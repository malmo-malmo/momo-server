package com.momo.domain.user.entity;

import static com.momo.UserFixture.getUser;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.common.LocationFixture.getLocation;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.user.domain.Location;
import com.momo.user.domain.SocialLogin;
import com.momo.user.domain.SocialProvider;
import com.momo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User POJO 테스트")
public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = getUserWithId();
    }

    @Test
    void 소셜_로그인_유저_생성_테스트() {
        User actual = User.createSocialLoginUser(SocialLogin.create(SocialProvider.KAKAO, "1"));
        Assertions.assertAll(
            () -> assertThat(actual.getSocialLogin().getProviderId()).isEqualTo("1"),
            () -> assertThat(actual.getSocialLogin().getProvider()).isEqualTo(SocialProvider.KAKAO)
        );
    }

    @Test
    void 유저_닉네임이_널인_경우_같은_닉네임_확인_테스트() {
        User user = User.builder().build();
        boolean expected = user.isSameNickname("nickname");
        assertThat(expected).isTrue();
    }

    @Test
    void 유저_닉네임이_널이_아닌_경우_같은_닉네임_확인_테스트() {
        boolean expected = user.isSameNickname(user.getNickname());
        assertThat(expected).isTrue();
    }

    @Test
    void 같은_유저_확인_테스트() {
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(1L).build();
        boolean expected = user1.isSameUser(user2);
        assertThat(expected).isTrue();
    }

    @Test
    void 다른_유저_확인_테스트() {
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(2L).build();
        boolean expected = user1.isSameUser(user2);
        assertThat(expected).isFalse();
    }

    @Test
    void 유저_업데이트_테스트() {
        User expectedUser = getUser();
        Location expectedLocation = getLocation();
        user.update(expectedUser, expectedLocation, expectedUser.getImageUrl());

        Assertions.assertAll(
            () -> assertThat(user.getNickname()).isEqualTo(expectedUser.getNickname()),
            () -> assertThat(user.getImageUrl()).isEqualTo(expectedUser.getImageUrl()),
            () -> assertThat(user.getLocation().getCity()).isEqualTo(expectedUser.getLocation().getCity()),
            () -> assertThat(user.getLocation().getDistrict()).isEqualTo(expectedLocation.getDistrict()),
            () -> assertThat(user.getLocation().getUniversity()).isEqualTo(expectedLocation.getUniversity())
        );
    }
}
