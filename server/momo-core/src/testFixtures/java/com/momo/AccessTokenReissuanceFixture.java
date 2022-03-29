package com.momo;

import static com.momo.common.FixtureComponents.DEVICE_CODE;

import com.momo.auth.domain.AccessTokenReissuance;

public class AccessTokenReissuanceFixture {

    public static AccessTokenReissuance getAccessTokenReissuance() {
        return AccessTokenReissuance.builder()
            .userId(1L)
            .deviceCode(DEVICE_CODE)
            .build();
    }
}
