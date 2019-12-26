package com.zhou.security.app;

import com.zhou.security.app.jwt.ZhouJwtTokenEnhancer;
import com.zhou.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author : Sun Chuan
 * @date : 2019/10/24 13:56
 * Description: TokenStore的实现类有5个，可以在yml里通过配置指定使用RedisTokenStore还是JwtTokenStore
 */
@Configuration
public class TokenStoreConfig {

    /***
     * RedisTokenStore需要一个连接工厂，这里可以直接注入进来
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /***
     * 将RedisTokenStore注入到spring容器
     * 当yml配置文件里配置了zhou.security.oauth2.tokenStore = redis时 ---> 下面的配置生效
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "zhou.security.oauth2", name = "tokenStore", havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /***
     * 当yml配置文件里配置了nrsc.security.oauth2.tokenStore = jwt或者根本就没配置该属性时 ---> 下面的配置生效
     */
    @Configuration
    @ConditionalOnProperty(prefix = "zhou.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {

        @Autowired
        private SecurityProperties securityProperties;

        /***
         * 配置JwtTokenStore ---> TokenStore只负责token的存储，不负责token的生成
         * @return
         */
        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /***
         * JwtAccessTokenConverter 其实就是一个TokenEnhancer
         * 通过阅读源码可知：TokenEnhancer是对生成的Token进行后续处理的（或者说增强），
         * 其实JwtAccessTokenConverter就是将默认生成的token做进一步处理使其成为一个JWT
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            //加入密签 --- 一定要保护好
            accessTokenConverter.setSigningKey(securityProperties.getoAuth2().getJwtSigningKey());
            return accessTokenConverter;
        }

        /***
         * 将自定义的TokenEnhancer注入到spring容器 --- 》可以覆盖该bean，实现自己的需求
         * @return
         */
        @Bean
//        @ConditionalOnBean(TokenEnhancer.class)
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer() {
            return new ZhouJwtTokenEnhancer();
        }
    }
}
