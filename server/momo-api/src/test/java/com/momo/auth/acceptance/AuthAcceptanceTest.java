package com.momo.auth.acceptance;

import static com.momo.UserFixture.getUser;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.assertThatOAuthLogin;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.assertThatRefreshLogin;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.assertThatRenewalRefreshToken;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.requestToOAuthLogin;
import static com.momo.auth.acceptance.step.AuthAcceptanceStep.requestToRefreshLogin;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.auth.dto.OAuthLoginRequest;
import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import com.momo.domain.auth.infra.TokenReissuanceDao;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
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
    private TokenReissuanceDao tokenReissuanceDao;

    private OAuthLoginRequest oAuthLoginRequest;

    private User socialLoginUser;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        oAuthLoginRequest = OAuthLoginRequest.builder()
            .authorizationCode("authorization_code")
            .provider("KAKAO")
            .deviceCode("device_code")
            .build();
        socialLoginUser = getUser();
    }

    @Test
    void 소셜_인가_코드로_로그인한다() {
        given(oAuthProviderFactory.getOAuthProvider(any())).willReturn(kaKaoOAuthProvider);
        given(kaKaoOAuthProvider.requestOAuthLogin(any())).willReturn(socialLoginUser);

        ExtractableResponse<Response> response = requestToOAuthLogin(oAuthLoginRequest);
        OAuthLoginResponse oAuthLoginResponse = getObject(response, OAuthLoginResponse.class);

        assertThatStatusIsOk(response);
        assertThatOAuthLogin(oAuthLoginResponse, existsAccessTokenReissuance(oAuthLoginResponse.getRefreshToken()));
    }

    @Test
    void 유효하지_않은_소셜_인가_코드로_로그인을_시도하면_실패한다() {
        given(oAuthProviderFactory.getOAuthProvider(any())).willReturn(kaKaoOAuthProvider);
        given(kaKaoOAuthProvider.requestOAuthLogin(any()))
            .willThrow(new CustomException(ErrorCode.INVALID_OAUTH_AUTHORIZATION_CODE));

        ExtractableResponse<Response> response = requestToOAuthLogin(oAuthLoginRequest);

        assertThatCustomException(response, ErrorCode.INVALID_OAUTH_AUTHORIZATION_CODE);
    }

    @Test
    void 리프레쉬_토큰과_기기번호로_엑세스_토큰을_재발급_받는다() {
        given(oAuthProviderFactory.getOAuthProvider(any())).willReturn(kaKaoOAuthProvider);
        given(kaKaoOAuthProvider.requestOAuthLogin(any())).willReturn(socialLoginUser);
        OAuthLoginResponse oAuthLoginResponse = getObject(
            requestToOAuthLogin(oAuthLoginRequest), OAuthLoginResponse.class
        );

        RefreshLoginRequest refreshLoginRequest = RefreshLoginRequest.builder()
            .refreshToken(oAuthLoginResponse.getRefreshToken())
            .deviceCode(oAuthLoginRequest.getDeviceCode())
            .build();

        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);
        OAuthLoginResponse refreshLoginResponse = getObject(response, OAuthLoginResponse.class);

        assertThatStatusIsOk(response);
        assertThatRefreshLogin(
            refreshLoginResponse,
            oAuthLoginResponse.getRefreshToken(),
            existsAccessTokenReissuance(refreshLoginResponse.getRefreshToken())
        );
    }

    @Test
    void 리프레쉬_토큰_만료기간이_24시간_이내면_새로운_리프레쉬_토큰을_발급한다() throws InterruptedException {
        given(oAuthProviderFactory.getOAuthProvider(any())).willReturn(kaKaoOAuthProvider);
        given(kaKaoOAuthProvider.requestOAuthLogin(any())).willReturn(socialLoginUser);
        OAuthLoginResponse oAuthLoginResponse = getObject(
            requestToOAuthLogin(oAuthLoginRequest), OAuthLoginResponse.class
        );

        Thread.sleep(1000);

        RefreshLoginRequest refreshLoginRequest = RefreshLoginRequest.builder()
            .refreshToken(oAuthLoginResponse.getRefreshToken())
            .deviceCode(oAuthLoginRequest.getDeviceCode())
            .build();
        given(tokenProvider.isOverRefreshTokenRenewalHour(refreshLoginRequest.getRefreshToken())).willReturn(false);

        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);
        OAuthLoginResponse refreshLoginResponse = getObject(response, OAuthLoginResponse.class);

        assertThatStatusIsOk(response);
        assertThatRenewalRefreshToken(
            refreshLoginResponse.getRefreshToken(),
            oAuthLoginResponse.getRefreshToken(),
            existsAccessTokenReissuance(refreshLoginResponse.getRefreshToken())
        );
    }

    @Test
    void 유효하지_않은_리프레쉬_토큰이면_엑세스_토큰_재발급을_실패한다() {
        given(oAuthProviderFactory.getOAuthProvider(any())).willReturn(kaKaoOAuthProvider);
        given(kaKaoOAuthProvider.requestOAuthLogin(any())).willReturn(socialLoginUser);
        requestToOAuthLogin(oAuthLoginRequest);

        RefreshLoginRequest refreshLoginRequest = RefreshLoginRequest.builder()
            .refreshToken("유효하지 않은 리프레쉬 토큰")
            .deviceCode(oAuthLoginRequest.getDeviceCode())
            .build();

        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);

        assertThatCustomException(response, ErrorCode.INVALID_REFRESH_TOKEN);
    }

    @Test
    void 유효하지_않은_기기번호면_엑세스_토큰_재발급을_실패한다() {
        given(oAuthProviderFactory.getOAuthProvider(any())).willReturn(kaKaoOAuthProvider);
        given(kaKaoOAuthProvider.requestOAuthLogin(any())).willReturn(socialLoginUser);
        OAuthLoginResponse oAuthLoginResponse = getObject(
            requestToOAuthLogin(oAuthLoginRequest), OAuthLoginResponse.class
        );

        RefreshLoginRequest refreshLoginRequest = RefreshLoginRequest.builder()
            .refreshToken(oAuthLoginResponse.getRefreshToken())
            .deviceCode("일치하지 않는 기기번호")
            .build();

        ExtractableResponse<Response> response = requestToRefreshLogin(refreshLoginRequest);

        assertThatCustomException(response, ErrorCode.INVALID_DEVICE_CODE);
    }

    private boolean existsAccessTokenReissuance(String refreshToken) {
        return tokenReissuanceDao.findByRefreshToken(refreshToken).isPresent();
    }
}
