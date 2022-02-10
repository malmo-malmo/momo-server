package com.momo.auth.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.auth.dto.OAuthLoginResponse;
import com.momo.domain.auth.dto.RefreshLoginRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;

public class AuthAcceptanceStep {

    public static void assertThatRefreshLogin(OAuthLoginResponse response) {
        Assertions.assertAll(
            () -> assertThat(response.getAccessTokenType()).isEqualTo("Bearer"),
            () -> assertThat(response.getAccessToken()).isNotNull(),
            () -> assertThat(response.getRefreshToken()).isNotNull()
        );
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
