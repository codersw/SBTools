package com.springboot.tools.entity.common;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.tools.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 当前登陆人信息
 * @author swen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String password;

    private String mobile;

    private Integer sex;

    private String sexName;

    private Integer deptId;

    private String deptName;

    private Set<String> permissions;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    public String getSexName() {
        return SexEnum.getNameByValue(this.sex);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
