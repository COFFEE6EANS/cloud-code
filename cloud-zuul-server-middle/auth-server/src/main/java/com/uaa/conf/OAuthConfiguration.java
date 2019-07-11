package com.uaa.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 这个类主要用于指定客户端ID、 密钥，以及权限定义与作用域声明， 指定TokenStore为
 * JWT， 不同于以往将TokenStore指定为Redis或是其他持久化工具， 主要的不同就是在这里。
 */
@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        endpoints
//                .tokenStore(jwtTokenStore())
//                .tokenEnhancer(jwtTokenConverter())
//                .authenticationManager(authenticationManager);
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(jwtTokenStore())
                .tokenEnhancer(jwtTokenConverter())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("zuul-server-middle")
                .secret("secret").scopes("WRIGTH", "read").autoApprove(true)
                .authorities("WRIGTH_READ", "WRIGTH_WRITE")
                .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code");
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("springcloudl23");
        return converter;
    }
}
