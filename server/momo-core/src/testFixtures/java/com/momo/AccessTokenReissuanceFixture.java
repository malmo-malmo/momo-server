package com.momo;

import static com.momo.common.FixtureComponents.DEVICE_CODE;
import static com.momo.common.FixtureComponents.REFRESH_TOKEN;

import com.momo.domain.auth.entity.AccessTokenReissuance;

public class AccessTokenReissuanceFixture {

    public static AccessTokenReissuance getAccessTokenReissuance() {
        return AccessTokenReissuance.builder()
            .refreshToken(REFRESH_TOKEN)
            .userId(1L)
            .deviceCode(DEVICE_CODE)
            .build();
    }
}
