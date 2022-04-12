package com.momo.favorite.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.dto.EnumResponse;
import com.momo.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.favorite.dto.FavoriteGroupCardResponse;
import com.momo.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.group.application.dto.GroupCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class FavoriteAcceptanceStep {

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

    public static void assertThatFindFavoriteCategories(List<EnumResponse> responses,
        FavoriteCategoriesUpdateRequest request) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(request.getFavoriteCategories().size()),
            () -> assertThat(responses.get(0).getName()).isEqualTo(request.getFavoriteCategories().get(0).getName()),
            () -> assertThat(responses.get(0).getCode()).isEqualTo(request.getFavoriteCategories().get(0).getCode()),
            () -> assertThat(responses.get(1).getName()).isEqualTo(request.getFavoriteCategories().get(1).getName()),
            () -> assertThat(responses.get(1).getCode()).isEqualTo(request.getFavoriteCategories().get(1).getCode())
        );
    }

    public static ExtractableResponse<Response> requestToCreateFavoriteGroup(String token,
        FavoriteGroupCreateRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/favorite/group")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindFavoriteGroupCount(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/favorite/group/count")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindFavoriteGroups(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/favorite/groups")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindFavoriteCategories(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/favorite/categories")
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
            .patch("/api/favorite/categories")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToDeleteFavoriteGroup(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .delete("/api/favorite/group/{groupId}", groupId)
            .then().log().all()
            .extract();
    }
}
