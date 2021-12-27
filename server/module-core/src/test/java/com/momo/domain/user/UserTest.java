package com.momo.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.user.domain.model.SocialProvider;
import com.momo.domain.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User POJO 테스트")
public class UserTest {

    @Test
    void 소셜_로그인_유저_생성_테스트() {
        User actual = User.createSocialLoginUser("1", SocialProvider.KAKAO);
        Assertions.assertAll(
            () -> assertThat(actual.getProviderId()).isEqualTo("1"),
            () -> assertThat(actual.getProvider()).isEqualTo(SocialProvider.KAKAO)
        );
    }

    @Test
    void 리프레쉬_토큰_수정_테스트() {
        User user = User.builder().build();
        user.updateRefreshToken("refreshToken");
        assertThat(user.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    void 유저_닉네임이_널인_경우_다른_닉네임_중복_확인_테스트() {
        User user = User.builder().build();
        boolean expected = user.isNotSameNickname("nickname");
        assertThat(expected).isTrue();
    }

    @Test
    void 유저_닉네임이_널이_아닌_경우_닉네임_중복_확인_테스트() {
        User user = User.builder().nickname("nickname").build();
        boolean expected = user.isNotSameNickname("nickname");
        assertThat(expected).isFalse();
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
