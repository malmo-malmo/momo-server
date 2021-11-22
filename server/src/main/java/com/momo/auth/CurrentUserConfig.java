package com.momo.auth;

import com.momo.auth.controller.CurrentUserArgumentResolver;
import com.momo.auth.service.OAuthService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CurrentUserConfig implements WebMvcConfigurer {

    private final OAuthService oAuthService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(createAuthenticationPrincipalArgumentResolver());
    }

    @Bean
    public CurrentUserArgumentResolver createAuthenticationPrincipalArgumentResolver() {
        return new CurrentUserArgumentResolver(oAuthService);
    }
}
