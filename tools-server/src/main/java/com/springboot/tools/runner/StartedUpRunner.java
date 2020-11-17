package com.springboot.tools.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@Component
public class StartedUpRunner implements ApplicationRunner {

    @Resource
    private ConfigurableApplicationContext context;

    @Value("${spring.application.name:}")
    private String applicationName;

    @Override
    public void run(ApplicationArguments args) {
        if (context.isActive()) {
            log.info("  _   _   _   _   _   _   _   _");
            log.info(" / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\");
            log.info("( | s | b | t | o | o | l | s |)");
            log.info(" \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/");
            log.info("{} 启动完毕，时间：{}", applicationName, LocalDateTime.now());
        }
    }
}
