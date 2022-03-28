package com.momo.domain.auth.domain;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.domain.SocialProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthProviderFactory {

    private final KakaoOAuthProvider kakaoOAuthProvider;

    public OAuthProvider getOAuthProvider(String provider) {
        if (provider.equalsIgnoreCase(SocialProvider.KAKAO.name())) {
            return kakaoOAuthProvider;
        }
        throw new CustomException(ErrorCode.SOCIAL_LOGIN_NOT_SUPPORT);
    }
}
