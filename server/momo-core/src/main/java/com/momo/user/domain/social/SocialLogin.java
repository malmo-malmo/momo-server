package com.momo.user.domain.social;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialLogin {

    @Enumerated(EnumType.STRING)
    private SocialProvider provider;

    private String providerId;

    @Builder
    public SocialLogin(SocialProvider provider, String providerId) {
        this.provider = provider;
        this.providerId = providerId;
    }

    public static SocialLogin create(SocialProvider provider, String providerId) {
        return SocialLogin.builder()
            .provider(provider)
            .providerId(providerId)
            .build();
    }
}
