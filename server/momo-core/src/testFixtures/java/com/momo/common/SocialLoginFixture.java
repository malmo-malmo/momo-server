package com.momo.common;

import static com.momo.common.FixtureComponents.PROVIDER;

import com.momo.user.domain.model.SocialLogin;

public class SocialLoginFixture {

    public static SocialLogin getSocialLogin() {
        return SocialLogin.builder()
            .provider(PROVIDER)
            .providerId("1")
            .build();
    }
}
