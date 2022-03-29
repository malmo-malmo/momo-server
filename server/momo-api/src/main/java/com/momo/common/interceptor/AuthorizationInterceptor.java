package com.momo.common.interceptor;

import static com.momo.Profile.TEST;

import com.momo.common.AuthorizationExtractor;
import com.momo.auth.infra.TokenProvider;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;
    private final Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (Arrays.asList(environment.getActiveProfiles()).contains(TEST)) {
            return true;
        }

        String accessToken = AuthorizationExtractor.extract(request);
        tokenProvider.validateAccessToken(accessToken);
        return true;
    }
}
