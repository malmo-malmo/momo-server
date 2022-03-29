package com.momo.auth.domain;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.user.entity.SocialProvider;
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
