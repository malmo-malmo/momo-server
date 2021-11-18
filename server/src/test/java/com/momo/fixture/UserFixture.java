package com.momo.fixture;

import com.momo.user.domain.model.Role;
import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;

public class UserFixture {

    public static final User USER1 = User.builder()
        .nickname("highright96")
        .providerId("1")
        .provider(SocialProvider.KAKAO)
        .role(Role.ROLE_USER)
        .build();

    public static final User USER2 = User.builder()
        .nickname("yoel")
        .providerId("2")
        .provider(SocialProvider.KAKAO)
        .role(Role.ROLE_USER)
        .build();
}
