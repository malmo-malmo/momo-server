package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
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
            () -> assertThat(response.getImage()).isEqualTo(user.getImageUrl()),
            () -> assertThat(response.getCity().getCode()).isEqualTo(user.getCity().getCode()),
            () -> assertThat(response.getCity().getName()).isEqualTo(user.getCity().getName()),
            () -> assertThat(response.getDistrict()).isEqualTo(user.getDistrict()),
            () -> assertThat(response.getUniversity()).isEqualTo(user.getUniversity()),
            () -> assertThat(response.getCategories().size()).isEqualTo(2)
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

    public static ExtractableResponse<Response> requestToUpdateFavoriteCategories(String token,
        FavoriteCategoriesUpdateRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .patch("/api/user/favorite-categories")
            .then().log().all()
            .extract();
    }
}
