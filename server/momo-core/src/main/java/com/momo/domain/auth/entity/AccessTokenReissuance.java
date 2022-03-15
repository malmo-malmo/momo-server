package com.momo.domain.auth.entity;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "access_token_reissuance", timeToLive = 604800)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenReissuance {

    @Id
    private String refreshToken;

    private Long userId;

    private String deviceCode;

    @Builder
    public AccessTokenReissuance(String refreshToken, Long userId, String deviceCode) {
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.deviceCode = deviceCode;
    }

    public static AccessTokenReissuance create(String refreshToken, Long userId, String deviceCode) {
        return AccessTokenReissuance.builder()
            .refreshToken(refreshToken)
            .userId(userId)
            .deviceCode(deviceCode)
            .build();
    }

    public boolean isSameDeviceCode(String deviceCode) {
        if (Objects.isNull(deviceCode)) {
            return false;
        }
        return this.deviceCode.equals(deviceCode);
    }
}
