package com.momo.domain.user.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User POJO 테스트")
public class UserTest {

    private User user;

    @BeforeEach
    void setup() {
        user = User.builder()
            .id(1L)
            .provider(SocialProvider.KAKAO)
            .providerId("1")
            .build();
    }

    @Test
    void 유저_닉네임이_널인_경우_같은_닉네임_확인_테스트() {
        boolean actual = user.isSameNickname("nickname");
        assertThat(actual).isFalse();
    }

    @Test
    void 유저_닉네임이_널이_아닌_경우_같은_닉네임_확인_테스트() {
        user = User.builder()
            .id(1L)
            .nickname("nickname")
            .build();
        boolean actual = user.isSameNickname("nickname");
        assertThat(actual).isTrue();
    }
}
