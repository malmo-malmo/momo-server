package com.momo.common.acceptance.step;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

public class AcceptanceStep {

  public static void assertThatStatusIsOk(ExtractableResponse<Response> response) {
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
  }

  public static void assertThatStatusIsCreated(ExtractableResponse<Response> response) {
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
  }

  public static void assertThatStatusIsNoContent(ExtractableResponse<Response> response) {
    assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
  }

  public static void assertThatStatusIsBadRequest(ExtractableResponse<Response> response) {
    assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  public static void assertThatStatusIsUnAuthorized(ExtractableResponse<Response> response) {
    assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
  }
}
