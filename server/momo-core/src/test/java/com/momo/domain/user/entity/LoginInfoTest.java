package com.momo.domain.user.entity;

import static com.momo.domain.user.entity.SocialProvider.KAKAO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LoginInfo POJO 테스트")
public class LoginInfoTest {

    @Test
    void 리프레쉬_토큰이_없는_로그인_정보를_생성한다() {
        LoginInfo expected = LoginInfo.createEmptyRefreshToken(KAKAO, "1");
        Assertions.assertAll(
            () -> assertThat(expected.getProvider()).isEqualTo(KAKAO),
            () -> assertThat(expected.getProviderId()).isEqualTo("1")
        );
    }

    @Test
    void 리프레쉬_토큰을_수정한다() {
        LoginInfo expected = LoginInfo.createEmptyRefreshToken(KAKAO, "1");
        expected.updateRefreshToken("refreshToken");
        assertThat(expected.getRefreshToken()).isEqualTo("refreshToken");
    }

    @Test
    void 인증_정보를_수정한다() {
        LoginInfo expected = LoginInfo.createEmptyRefreshToken(KAKAO, "1");
        expected.updateAuthInfo("refreshToken", "deviceCode");
        Assertions.assertAll(
            () -> assertThat(expected.getRefreshToken()).isEqualTo("refreshToken"),
            () -> assertThat(expected.getDeviceCode()).isEqualTo("deviceCode")
        );
    }

    @Test
    void 기기_고유번호가_동일한지_확인한다() {
        LoginInfo loginInfo = LoginInfo.createEmptyRefreshToken(KAKAO, "1");
        loginInfo.updateAuthInfo("refreshToken", "deviceCode");

        boolean expected = loginInfo.isSameDeviceCode("deviceCode");

        assertThat(expected).isTrue();
    }

    @Test
    void 기기_고유번호가_널인_경우_동일한지_확인한다() {
        LoginInfo loginInfo = LoginInfo.createEmptyRefreshToken(KAKAO, "1");
        loginInfo.updateAuthInfo("refreshToken", "deviceCode");

        boolean expected = loginInfo.isSameDeviceCode(null);

        assertThat(expected).isFalse();
    }
}
