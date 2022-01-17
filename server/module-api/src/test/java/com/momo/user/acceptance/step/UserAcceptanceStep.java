package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.user.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.user.dto.FavoriteGroupCardResponse;
import com.momo.domain.user.dto.FavoriteGroupCreateRequest;
import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.entity.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
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

    public static void assertThatFindFavoriteGroups(List<FavoriteGroupCardResponse> responses,
        GroupCreateRequest request) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getId()).isNotNull(),
            () -> assertThat(responses.get(0).getGroupCardResponse().getId()).isNotNull(),
            () -> assertThat(responses.get(0).getGroupCardResponse().getName()).isEqualTo(request.getName()),
            () -> assertThat(responses.get(0).getGroupCardResponse().getStartDate()).isEqualTo(request.getStartDate()),
            () -> assertThat(responses.get(0).getGroupCardResponse().getImageUrl()).isNotNull(),
            () -> assertThat(responses.get(0).getGroupCardResponse().getParticipantCnt()).isEqualTo(1)
        );
    }

    public static ExtractableResponse<Response> requestToCreateFavoriteGroup(String token,
        FavoriteGroupCreateRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/user/favorite-group")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindMyInformation(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindFavoriteGroupCount(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/user/favorite-group-count")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindFavoriteGroups(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/user/favorite-groups")
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

    public static ExtractableResponse<Response> requestToDeleteFavoriteGroup(String token, Long favoriteId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .delete("/api/user/favorite-group/{id}", favoriteId)
            .then().log().all()
            .extract();
    }
}
