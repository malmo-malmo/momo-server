package com.momo.fixture;

import static com.momo.domain.group.entity.Category.HEALTH;
import static com.momo.domain.group.entity.Category.HOBBY;
import static com.momo.domain.group.entity.Category.SELF_DEVELOPMENT;
import static com.momo.domain.group.entity.Category.STOCK;

import com.momo.domain.district.entity.City;
import com.momo.domain.user.entity.Location;
import com.momo.domain.user.entity.Social;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.util.List;

public class UserFixture {

    public static User getUser1() {
        User user = User.builder()
            .nickname("user1")
            .location(Location.builder()
                .university("대학교1")
                .city(City.SEOUL)
                .district("강동구")
                .build())
            .loginInfo(Social.from(SocialProvider.KAKAO, "1"))
            .build();
        user.updateFavoriteCategories(List.of(HEALTH, SELF_DEVELOPMENT));
        return user;
    }

    public static User getUser2() {
        User user = User.builder()
            .nickname("user2")
            .location(Location.builder()
                .university("대학교2")
                .city(City.SEOUL)
                .district("강동구")
                .build())
            .loginInfo(Social.from(SocialProvider.KAKAO, "2"))
            .build();
        user.updateFavoriteCategories(List.of(HOBBY));
        return user;
    }

    public static User getUser3() {
        User user = User.builder()
            .nickname("user3")
            .location(Location.builder()
                .university("대학교1")
                .city(City.SEOUL)
                .district("강남구")
                .build())
            .loginInfo(Social.builder()
                .providerId("3")
                .provider(SocialProvider.KAKAO)
                .build())
            .build();
        user.updateFavoriteCategories(List.of(STOCK));
        return user;
    }
}
