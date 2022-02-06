package com.momo.domain.user.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User POJO 테스트")
public class UserTest {

    @Test
    void 소셜_로그인_유저_생성_테스트() {
        User actual = User.createSocialLoginUser(LoginInfo.from(SocialProvider.KAKAO, "1"));
        Assertions.assertAll(
            () -> assertThat(actual.getLoginInfo().getProviderId()).isEqualTo("1"),
            () -> assertThat(actual.getLoginInfo().getProvider()).isEqualTo(SocialProvider.KAKAO)
        );
    }

    @Test
    void 리프레쉬_토큰_수정_테스트() {
        User user = User.builder().loginInfo(LoginInfo.builder().build()).build();
        user.updateRefreshToken("refreshToken");
        assertThat(user.getLoginInfo().getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    void 유저_닉네임이_널인_경우_같은_닉네임_확인_테스트() {
        User user = User.builder().build();
        boolean expected = user.isSameNickname("nickname");
        assertThat(expected).isTrue();
    }

    @Test
    void 유저_닉네임이_널이_아닌_경우_같은_닉네임_확인_테스트() {
        User user = User.builder().nickname("nickname").build();
        boolean expected = user.isSameNickname("nickname");
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
}
