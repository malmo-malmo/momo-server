package com.momo.domain.user.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class LoginInfo {

    @Enumerated(EnumType.STRING)
    private SocialProvider provider;

    @Column(nullable = false)
    private String providerId;

    private String refreshToken;

    @Builder
    public LoginInfo(SocialProvider provider, String providerId, String refreshToken) {
        this.provider = provider;
        this.providerId = providerId;
        this.refreshToken = refreshToken;
    }

    public static LoginInfo createEmptyRefreshToken(SocialProvider provider, String providerId) {
        return LoginInfo.builder()
            .provider(provider)
            .providerId(providerId)
            .build();
    }
    public static LoginInfo create(SocialProvider provider, String providerId, String refreshToken) {
        return LoginInfo.builder()
            .provider(provider)
            .providerId(providerId)
            .refreshToken(refreshToken)
            .build();
    }

    void updateRefreshToken(String refreshToken) {
        if(Objects.isNull(refreshToken)) {
            return;
        }
        this.refreshToken = refreshToken;
    }
}
