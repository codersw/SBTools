package com.springboot.tools.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import com.google.code.kaptcha.Producer;
import com.springboot.tools.config.RedisCache;
import com.springboot.tools.constant.Constants;
import com.springboot.tools.entity.common.Result;
import com.springboot.tools.entity.common.ResultGenerator;
import com.springboot.tools.entity.vo.CaptchaImageVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@RestController
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Resource
    private RedisCache redisCache;
    
    // 验证码类型
    @Value("${captcha.type}")
    private String captchaType;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result<?> getCode(HttpServletResponse response) throws IOException {
        // 保存验证码信息
        String uuid = UUID.fastUUID().toString();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String capStr, code = null;
        BufferedImage image = null;
        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            assert image != null;
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult(CaptchaImageVo.builder().uuid(uuid).img(Base64.encode(os.toByteArray())).build());
    }
}
