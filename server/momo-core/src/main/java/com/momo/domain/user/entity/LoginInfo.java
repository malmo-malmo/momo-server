package com.momo.domain.user.entity;

import java.util.Objects;
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

    private String providerId;

    private String refreshToken;

    private String deviceCode;

    @Builder
    public LoginInfo(SocialProvider provider, String providerId, String refreshToken, String deviceCode) {
        this.provider = provider;
        this.providerId = providerId;
        this.refreshToken = refreshToken;
        this.deviceCode = deviceCode;
    }

    public static LoginInfo createEmptyRefreshToken(SocialProvider provider, String providerId) {
        return LoginInfo.builder()
            .provider(provider)
            .providerId(providerId)
            .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateAuthInfo(String refreshToken, String deviceCode) {
        this.refreshToken = refreshToken;
        this.deviceCode = deviceCode;
    }

    public boolean isSameDeviceCode(String deviceCode) {
        if (Objects.isNull(deviceCode)) {
            return false;
        }
        return this.deviceCode.equals(deviceCode);
    }
}
