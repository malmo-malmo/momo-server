package com.momo;

import static com.momo.common.FixtureComponents.CATEGORIES;
import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.NICKNAME;
import static com.momo.common.LocationFixture.getLocation;
import static com.momo.common.SocialLoginFixture.getSocialLogin;

import com.momo.group.entity.Category;
import com.momo.user.domain.model.User;
import java.util.List;

public class UserFixture {

    public static User getUser() {
        INCREASE_ID++;
        User user = User.builder()
            .nickname(NICKNAME + INCREASE_ID)
            .location(getLocation())
            .socialLogin(getSocialLogin())
            .build();
        user.updateFavoriteCategories(CATEGORIES);
        return user;
    }

    public static User getUser(String district, String university, List<Category> categories) {
        INCREASE_ID++;
        User user = User.builder()
            .nickname(NICKNAME + INCREASE_ID)
            .location(getLocation(district, university))
            .socialLogin(getSocialLogin())
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
            .socialLogin(getSocialLogin())
            .build();
        user.updateFavoriteCategories(CATEGORIES);
        return user;
    }
}
