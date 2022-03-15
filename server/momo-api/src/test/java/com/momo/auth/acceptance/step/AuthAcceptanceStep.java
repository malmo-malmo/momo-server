package com.momo.auth.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.auth.dto.OAuthLoginRequest;
import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;

public class AuthAcceptanceStep {

    public static void assertThatOAuthLogin(OAuthLoginResponse response, boolean isPresentRefreshTokenInRedis) {
        Assertions.assertAll(
            () -> assertThat(response.getAccessTokenType()).isEqualTo("Bearer"),
            () -> assertThat(response.getAccessToken()).isNotNull(),
            () -> assertThat(response.getRefreshToken()).isNotNull(),
            () -> assertThat(isPresentRefreshTokenInRedis).isTrue()
        );
    }

    public static void assertThatRefreshLogin(OAuthLoginResponse response, String refreshToken,
        boolean isPresentRefreshTokenInRedis) {
        Assertions.assertAll(
            () -> assertThat(response.getAccessTokenType()).isEqualTo("Bearer"),
            () -> assertThat(response.getAccessToken()).isNotNull(),
            () -> assertThat(response.getRefreshToken()).isEqualTo(refreshToken),
            () -> assertThat(isPresentRefreshTokenInRedis).isTrue()
        );
    }

    public static void assertThatRenewalRefreshToken(String newRefreshToken, String prevRefreshToken,
        boolean isPresentPrevRefreshTokenInRedis, boolean isPresentRenewalRefreshTokenInRedis) {
        ;
        Assertions.assertAll(
            () -> assertThat(newRefreshToken).isNotEqualTo(prevRefreshToken),
            () -> assertThat(isPresentPrevRefreshTokenInRedis).isFalse(),
            () -> assertThat(isPresentRenewalRefreshTokenInRedis).isTrue()
        );
    }

    public static ExtractableResponse<Response> requestToOAuthLogin(OAuthLoginRequest request) {
        return given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/api/oauth/login")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToRefreshLogin(RefreshLoginRequest refreshLoginRequest) {
        return given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(refreshLoginRequest)
            .post("/api/oauth/login/refresh")
            .then().log().all()
            .extract();
    }
}
