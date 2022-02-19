package com.momo.common;

import static com.momo.common.FixtureComponents.PROVIDER;
import static com.momo.common.FixtureComponents.REFRESH_TOKEN;

import com.momo.domain.user.entity.LoginInfo;

public class SocialFixture {

    public static LoginInfo getLoginInfo() {
        return LoginInfo.builder()
            .provider(PROVIDER)
            .providerId("1")
            .refreshToken(REFRESH_TOKEN)
            .build();
    }
}
