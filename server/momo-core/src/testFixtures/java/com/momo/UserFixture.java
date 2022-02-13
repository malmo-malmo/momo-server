package com.momo;

import static com.momo.common.LocationFixture.getLocation;
import static com.momo.common.SocialFixture.getSocial;
import static com.momo.common.FixtureComponents.CATEGORIES;
import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.NICKNAME;

import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.User;
import java.util.List;

public class UserFixture {

    public static User getUser() {
        INCREASE_ID++;
        User user = User.builder()
            .nickname(NICKNAME + INCREASE_ID)
            .location(getLocation())
            .loginInfo(getSocial())
            .build();
        user.updateFavoriteCategories(CATEGORIES);
        return user;
    }

    public static User getUser(String district, String university, List<Category> categories) {
        INCREASE_ID++;
        User user = User.builder()
            .nickname(NICKNAME + INCREASE_ID)
            .location(getLocation(district, university))
            .loginInfo(getSocial())
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
            .location(getLocation())
            .loginInfo(getSocial())
            .build();
        user.updateFavoriteCategories(CATEGORIES);
        return user;
    }
}
