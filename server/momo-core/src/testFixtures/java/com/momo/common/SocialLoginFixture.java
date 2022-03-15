package com.momo.common;

import static com.momo.common.FixtureComponents.PROVIDER;

import com.momo.domain.user.entity.SocialLogin;

public class SocialLoginFixture {

    public static SocialLogin getSocialLogin() {
        return SocialLogin.builder()
            .provider(PROVIDER)
            .providerId("1")
            .build();
    }
}
