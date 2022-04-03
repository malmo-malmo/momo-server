package com.momo.auth.domain;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenReissuance {

    private Long userId;
    private String deviceCode;

    @Builder
    public AccessTokenReissuance(Long userId, String deviceCode) {
        this.userId = userId;
        this.deviceCode = deviceCode;
    }

    public static AccessTokenReissuance create(Long userId, String deviceCode) {
        return AccessTokenReissuance.builder()
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
