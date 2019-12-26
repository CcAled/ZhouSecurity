package com.zhou.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * zhousecurity
 * 2019/11/8 17:30
 *
 * @author ChristChou
 * @since
 **/
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * 参数为springsocial的过滤器
     * @param socialAuthenticationFilter
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}