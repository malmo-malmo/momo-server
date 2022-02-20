package com.momo.auth.acceptance;

import static com.momo.UserFixture.getUser;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.assertThatRefreshLogin;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.requestToRefreshLogin;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.domain.common.exception.ErrorCode.INVALID_DEVICE_CODE;
import static com.momo.domain.common.exception.ErrorCode.INVALID_REFRESH_TOKEN;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import com.momo.domain.auth.provider.TokenProvider;
import com.momo.domain.user.entity.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("소셜 로그인 통합/인수 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    @Autowired
    private TokenProvider tokenProvider;

    private String refreshToken;
    private String deviceCode;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        User user = userRepository.save(getUser());
        refreshToken = tokenProvider.createRefreshToken(user);
        deviceCode = "deviceCode";
        updateRefreshToken(user, refreshToken);
    }

    @Test
    void 리프레쉬_토큰으로_로그인한다() {
        RefreshLoginRequest refreshLoginRequest = new RefreshLoginRequest(refreshToken, deviceCode);
        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);
        OAuthLoginResponse oAuthLoginResponse = getObject(response, OAuthLoginResponse.class);
        assertThatStatusIsOk(response);
        assertThatRefreshLogin(oAuthLoginResponse, refreshToken);
        //발급받은 엑세스 토큰 확인
        assertThatStatusIsOk(requestToFindMyInformation(oAuthLoginResponse.getAccessToken()));
    }

    @Test
    void 잘못된_리프레쉬_토큰으로_로그인하면_실패한다() {
        RefreshLoginRequest refreshLoginRequest = new RefreshLoginRequest("rt", deviceCode);
        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);
        assertThatCustomException(response, INVALID_REFRESH_TOKEN);
    }

    @Test
    void 잘못된_기기_고유번호로_로그인하면_실패한다() {
        RefreshLoginRequest refreshLoginRequest = new RefreshLoginRequest(refreshToken, "dc");
        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);
        assertThatCustomException(response, INVALID_DEVICE_CODE);
    }

    @Test
    void 리프레쉬_토큰_남은_만료시간이_24시간_이내면_리프레쉬_토큰을_발급한다() {
        User customUser = userRepository.save(getUser());
        String customRefreshToken = tokenProvider.createRefreshTokenForTest(customUser, 86400000);
        updateRefreshToken(customUser, customRefreshToken);

        RefreshLoginRequest refreshLoginRequest = new RefreshLoginRequest(customRefreshToken, deviceCode);
        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);

        assertThatStatusIsOk(response);
        assertThat(getObject(response, OAuthLoginResponse.class).getRefreshToken()).isNotEqualTo(customRefreshToken);
    }

    private void updateRefreshToken(User user, String refreshToken) {
        user.getLoginInfo().updateAuthInfo(refreshToken, deviceCode);
        userRepository.save(user);
    }
}
