package com.momo.unit.user.domain;

import static com.momo.UserFixture.getUserWithId;
import static com.momo.common.LocationFixture.getLocation;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.user.domain.location.Location;
import com.momo.user.domain.social.SocialLogin;
import com.momo.user.domain.social.SocialProvider;
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
    @DisplayName("소셜 로그인 유저를 생성한다")
    void createUser_Social_Success() {
        User actual = User.createSocialLoginUser(SocialLogin.create(SocialProvider.KAKAO, "1"));
        Assertions.assertAll(
            () -> assertThat(actual.getSocialLogin().getProviderId()).isEqualTo("1"),
            () -> assertThat(actual.getSocialLogin().getProvider()).isEqualTo(SocialProvider.KAKAO)
        );
    }

    @Test
    @DisplayName("동일한 닉네임인지 확인한다 - 닉네임이 NULL인 경우")
    void isSameNickname_Null_True() {
        User user = User.builder().build();
        boolean expected = user.isSameNickname("nickname");
        assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("동일한 닉네임인지 확인한다 - 닉네임이 NULL이 아닌 경우")
    void isSameNickname_NonNull_True() {
        boolean expected = user.isSameNickname(user.getNickname());
        assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("같은 유저인지 확인한다")
    void isSameUser_True() {
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(1L).build();
        boolean expected = user1.equals(user2);
        assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("다른 유저인지 확인한다")
    void isSameUser_False() {
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(2L).build();
        boolean expected = user1.equals(user2);
        assertThat(expected).isFalse();
    }

    @Test
    @DisplayName("유저 정보를 수정한다")
    void updateUser_LoginUser_Success() {
        String nickname = "변경할 닉네임";
        Location location = getLocation();
        user.update(nickname, location);

        Assertions.assertAll(
            () -> assertThat(user.getNickname()).isEqualTo(nickname),
            () -> assertThat(user.getLocation().getCity()).isEqualTo(location.getCity()),
            () -> assertThat(user.getLocation().getDistrict()).isEqualTo(location.getDistrict()),
            () -> assertThat(user.getLocation().getUniversity()).isEqualTo(location.getUniversity())
        );
    }
}
