package com.momo.api.auth;

import com.momo.common.AuthorizationExtractor;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import com.momo.domain.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final OAuthService oAuthService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String accessToken = AuthorizationExtractor
            .extract(Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class)));
        return oAuthService.findLoginUserByAccessToken(accessToken);
    }
}
