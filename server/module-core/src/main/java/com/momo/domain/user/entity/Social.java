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
public class Social {

    @Enumerated(EnumType.STRING)
    private SocialProvider provider;

    @Column(nullable = false)
    private String providerId;

    private String refreshToken;

    @Builder
    public Social(SocialProvider provider, String providerId, String refreshToken) {
        this.provider = provider;
        this.providerId = providerId;
        this.refreshToken = refreshToken;
    }

    public static Social createEmptyRefreshToken(SocialProvider provider, String providerId) {
        return Social.builder()
            .provider(provider)
            .providerId(providerId)
            .build();
    }
    public static Social create(SocialProvider provider, String providerId, String refreshToken) {
        return Social.builder()
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