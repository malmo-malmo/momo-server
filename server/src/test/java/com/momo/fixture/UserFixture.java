package com.momo.fixture;

import com.momo.user.domain.model.Location;
import com.momo.user.domain.model.Role;
import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;

public class UserFixture {

    public static final User USER1 = User.builder()
        .nickname("user1")
        .university("대학교1")
        .categories("HEALTH,SELF_DEVELOPMENT")
        .location(Location.GANGDONG_GU)
        .providerId("1")
        .provider(SocialProvider.KAKAO)
        .role(Role.ROLE_USER)
        .build();

    public static final User USER2 = User.builder()
        .nickname("user2")
        .university("대학교2")
        .location(Location.GANGDONG_GU)
        .providerId("2")
        .provider(SocialProvider.KAKAO)
        .role(Role.ROLE_USER)
        .build();

    public static final User USER3 = User.builder()
        .nickname("user3")
        .university("대학교1")
        .location(Location.GANGNAM_GU)
        .providerId("3")
        .provider(SocialProvider.KAKAO)
        .role(Role.ROLE_USER)
        .build();
}
