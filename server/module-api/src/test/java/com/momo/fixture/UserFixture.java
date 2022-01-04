package com.momo.fixture;

import static com.momo.domain.group.entity.Category.HEALTH;
import static com.momo.domain.group.entity.Category.HOBBY;
import static com.momo.domain.group.entity.Category.SELF_DEVELOPMENT;
import static com.momo.domain.group.entity.Category.STOCK;

import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.util.List;

public class UserFixture {

    public static User getUser1() {
        User user = User.builder()
            .nickname("UserFixture.getUser1()")
            .university("대학교1")
            .city("서울시")
            .district("강동구")
            .providerId("1")
            .provider(SocialProvider.KAKAO)
            .build();
        user.updateFavoriteCategories(List.of(HEALTH, SELF_DEVELOPMENT));
        return user;
    }

    public static User getUser2() {
        User user = User.builder()
            .nickname("UserFixture.getUser2()")
            .university("대학교2")
            .city("서울시")
            .district("강동구")
            .providerId("2")
            .provider(SocialProvider.KAKAO)
            .build();
        user.updateFavoriteCategories(List.of(HOBBY));
        return user;
    }

    public static User getUser3() {
        User user = User.builder()
            .nickname("UserFixture.getUser3()")
            .university("대학교1")
            .city("서울시")
            .district("강남구")
            .providerId("3")
            .provider(SocialProvider.KAKAO)
            .build();
        user.updateFavoriteCategories(List.of(STOCK));
        return user;
    }
}
