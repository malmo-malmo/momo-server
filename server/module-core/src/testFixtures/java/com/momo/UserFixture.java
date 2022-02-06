package com.momo;

import static com.momo.domain.district.entity.City.SEOUL;
import static com.momo.domain.group.entity.Category.HEALTH;
import static com.momo.domain.group.entity.Category.SELF_DEVELOPMENT;
import static com.momo.domain.user.entity.SocialProvider.KAKAO;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.util.List;

public class UserFixture {

    private static final String NICKNAME = "닉네임";
    private static final String UNIVERSITY = "대학교";
    private static final City CITY = SEOUL;
    private static final String DISTRICT = "강동구";
    private static final SocialProvider PROVIDER = KAKAO;
    private static final List<Category> CATEGORIES = List.of(HEALTH, SELF_DEVELOPMENT);

    private static Long INCREASE_ID = 0L;

    public static User getUser() {
        INCREASE_ID++;
        User user = User.builder()
            .nickname(NICKNAME + INCREASE_ID)
            .university(UNIVERSITY)
            .city(CITY)
            .district(DISTRICT)
            .provider(PROVIDER)
            .providerId(String.valueOf(INCREASE_ID))
            .build();
        user.updateFavoriteCategories(CATEGORIES);
        return user;
    }

    public static User getUserWithId() {
        INCREASE_ID++;
        User user = User.builder()
            .id(INCREASE_ID)
            .nickname(NICKNAME + INCREASE_ID)
            .university(UNIVERSITY)
            .city(CITY)
            .district(DISTRICT)
            .provider(PROVIDER)
            .providerId(String.valueOf(INCREASE_ID))
            .build();
        user.updateFavoriteCategories(CATEGORIES);
        return user;
    }

    public static User getCustomUser(String university, City city, String district, List<Category> categories) {
        INCREASE_ID++;
        User user = User.builder()
            .nickname(NICKNAME + INCREASE_ID)
            .university(university)
            .city(city)
            .district(district)
            .provider(PROVIDER)
            .providerId(String.valueOf(INCREASE_ID))
            .build();
        user.updateFavoriteCategories(categories);
        return user;
    }
}
