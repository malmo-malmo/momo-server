package com.momo;

import static com.momo.common.FixtureComponents.CATEGORIES;
import static com.momo.common.FixtureComponents.CITY;
import static com.momo.common.FixtureComponents.DISTRICT;
import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.NICKNAME;
import static com.momo.common.FixtureComponents.PROVIDER;
import static com.momo.common.FixtureComponents.UNIVERSITY;

import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.User;
import java.util.List;

public class UserFixture {

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

    public static User getUser(String district, String university, List<Category> categories) {
        INCREASE_ID++;
        User user = User.builder()
            .nickname(NICKNAME + INCREASE_ID)
            .university(university)
            .city(CITY)
            .district(district)
            .provider(PROVIDER)
            .providerId(String.valueOf(INCREASE_ID))
            .build();
        user.updateFavoriteCategories(categories);
        return user;
    }

    public static User getUserWithId() {
        INCREASE_ID++;
        User user = User.builder()
            .id(INCREASE_ID)
            .nickname(NICKNAME + INCREASE_ID)
            .imageUrl(IMAGE_URL)
            .university(UNIVERSITY)
            .city(CITY)
            .district(DISTRICT)
            .provider(PROVIDER)
            .providerId(String.valueOf(INCREASE_ID))
            .build();
        user.updateFavoriteCategories(CATEGORIES);
        return user;
    }
}
