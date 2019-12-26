/**
 *
 */
package com.zhou.security.browser;

import javax.sql.DataSource;

import com.zhou.security.browser.authentication.ZhouAuthenctiationFailureHandler;
import com.zhou.security.browser.authentication.ZhouAuthenticationSuccessHandler;
import com.zhou.security.browser.session.ZhouExpiredSessionStrategy;
import com.zhou.security.core.authentication.AbstractChannelSecurityConfig;
import com.zhou.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zhou.security.core.authorize.AuthorizeConfigManager;
import com.zhou.security.core.properties.SecurityConstants;
import com.zhou.security.core.properties.SecurityProperties;
import com.zhou.security.core.validate.code.ValidateCodeFilter;
import com.zhou.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * @author zhailiang
 *
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer zhouSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        http.apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(zhouSocialSecurityConfig)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy)
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                    .maxSessionsPreventsLogin(true)
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)
                    .and()
                    .and()
                .logout()
                    .logoutUrl("/signOut")
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .deleteCookies("JSESSIONID")
                    .and()
//                .authorizeRequests()
//                .antMatchers(
//                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//                        securityProperties.getBrowser().getLoginPage(),
//                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
//                        securityProperties.getBrowser().getSignUpUrl(),
//                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
//                        "/user/regist","/demo-logout.html")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }
}
