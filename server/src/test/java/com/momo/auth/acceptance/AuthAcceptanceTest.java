package com.momo.auth.acceptance;

import static com.momo.auth.acceptance.step.AuthAcceptanceStep.assertThatRefreshLogin;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.requestToRefreshLogin;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.exception.ErrorCode.INVALID_REFRESH_TOKEN;
import static com.momo.fixture.UserFixture.USER1;

import com.momo.auth.TokenProvider;
import com.momo.auth.controller.dto.OAuthLoginResponse;
import com.momo.auth.controller.dto.RefreshLoginRequest;
import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.exception.ErrorCode;
import com.momo.user.acceptance.step.UserAcceptanceStep;
import com.momo.user.domain.model.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("소셜 로그인 통합/인수 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    @Autowired
    TokenProvider tokenProvider;

    private String refreshToken;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        User savedUser = userRepository.save(USER1);
        refreshToken = tokenProvider.createRefreshToken(savedUser);
        savedUser.updateRefreshToken(refreshToken);
        userRepository.save(savedUser);
    }

    @Test
    public void 리프레쉬_토큰으로_로그인한다() {
        RefreshLoginRequest refreshLoginRequest = new RefreshLoginRequest(refreshToken);
        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);
        OAuthLoginResponse oAuthLoginResponse = getObject(response, OAuthLoginResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatRefreshLogin(oAuthLoginResponse);

        //새로 발급받은 엑세스 토큰 확인
        AcceptanceStep
            .assertThatStatusIsOk(UserAcceptanceStep.requestToFindMyInformation(oAuthLoginResponse.getAccessToken()));
    }

    @Test
    public void 잘못된_리프레쉬_토큰으로_로그인하면_실패한다() {
        RefreshLoginRequest refreshLoginRequest = new RefreshLoginRequest("invalidRefreshToken");
        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);
        assertThatCustomException(response, INVALID_REFRESH_TOKEN);
    }
}
