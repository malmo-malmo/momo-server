package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;

import com.momo.common.dto.GroupCategoryRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UserAcceptanceStep {

  public static ExtractableResponse<Response> requestToUpdateGroupCategory(String token,
      GroupCategoryRequest request) {
    return given().log().all()
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(request)
        .when()
        .patch("/api/user/categories")
        .then().log().all()
        .extract();
  }
}
