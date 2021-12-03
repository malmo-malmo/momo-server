package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.controller.dto.CategoryRequest;
import com.momo.user.controller.dto.UserResponse;
import com.momo.user.controller.dto.UserUpdateRequest;
import com.momo.user.domain.model.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UserAcceptanceStep {

    public static void assertThatFindMyInformation(UserResponse response, User user) {
        Assertions.assertAll(
            () -> assertThat(response.getId()).isNotNull(),
            () -> assertThat(response.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(response.getImage()).isEqualTo(user.getImage()),
            () -> assertThat(response.getCity()).isEqualTo(user.getCity()),
            () -> assertThat(response.getDistrict()).isEqualTo(user.getDistrict()),
            () -> assertThat(response.getUniversity()).isEqualTo(user.getUniversity())
        );
    }

    public static ExtractableResponse<Response> requestToFindMyInformation(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdate(String token,
        UserUpdateRequest userUpdateRequest) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(userUpdateRequest)
            .when()
            .patch("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdateCategory(String token,
        CategoryRequest request) {
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
