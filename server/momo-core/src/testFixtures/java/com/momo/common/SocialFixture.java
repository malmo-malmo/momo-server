package com.momo.common;

import static com.momo.common.FixtureComponents.PROVIDER;
import static com.momo.common.FixtureComponents.REFRESH_TOKEN;

import com.momo.domain.user.entity.Social;

public class SocialFixture {

    public static Social getSocial() {
        return Social.builder()
            .provider(PROVIDER)
            .providerId("1")
            .refreshToken(REFRESH_TOKEN)
            .build();
    }
}
