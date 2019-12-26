package com.zhou.security.app;

import com.zhou.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.zhou.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zhou.security.core.authorize.AuthorizeConfigManager;
import com.zhou.security.core.properties.SecurityConstants;
import com.zhou.security.core.properties.SecurityProperties;
import com.zhou.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * zhousecurity
 * 2019/11/8 15:20
 *
 * @author ChristChou
 * @since
 **/
@Configuration
@EnableResourceServer
public class ResourcesServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler zhouAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler zhouAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer zhouSocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(zhouAuthenticationSuccessHandler)
                .failureHandler(zhouAuthenticationFailureHandler);

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .apply(zhouSocialSecurityConfig)
                .and()
            .apply(openIdAuthenticationSecurityConfig)
                .and()
//            .authorizeRequests()
//                .antMatchers(
//                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//                securityProperties.getBrowser().getLoginPage(),
//                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
//                securityProperties.getBrowser().getSignUpUrl(),
//                securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
//                "/user/regist","/demo-logout.html","/social/signUp")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
            .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
