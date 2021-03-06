package com.momo.config;

import com.momo.auth.infra.TokenProvider;
import com.momo.common.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig {

    private final TokenProvider tokenProvider;
    private final Environment environment;

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor(tokenProvider, environment);
    }
}
