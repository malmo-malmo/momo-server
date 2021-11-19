package com.momo.group.acceptance.step;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.user.domain.model.Location;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GroupAcceptanceStep {

    public static void assertThatFindGroup(GroupCreateRequest request, GroupResponse response, boolean isManager,
        boolean isParticipant, String university) {
        Assertions.assertAll(
            () -> assertThat(response.getName()).isEqualTo(request.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo(request.getImageUrl()),
            () -> assertThat(response.getStartDate()).isEqualTo(request.getStartDate()),
            () -> assertThat(response.getUniversity()).isEqualTo(university),
            () -> assertThat(response.getLocation()).isEqualTo(Location.of(request.getLocation()).getName()),
            () -> assertThat(response.isOffline()).isEqualTo(request.getIsOffline()),
            () -> assertThat(response.getIntroduction()).isEqualTo(request.getIntroduction()),
            () -> assertThat(response.getParticipantCnt()).isEqualTo(1L),
            () -> assertThat(response.isParticipant()).isEqualTo(isParticipant),
            () -> assertThat(response.isManager()).isEqualTo(isManager)
        );
    }

    public static void assertThatFindCategory(List<EnumResponse> responses) {
        assertThat(EnumResponse.listOfCategory().size()).isEqualTo(responses.size());
    }

    public static ExtractableResponse<Response> requestToCreateGroup(String token,
        GroupCreateRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/api/group")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindGroup(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("groupId", groupId)
            .get("/api/group")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindGroupsByUserUniversity(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("page", 0)
            .param("size", 10)
            .when()
            .get("/api/groups/user-university/paging")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindGroupsByLocation(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("page", 0)
            .param("size", 10)
            .when()
            .get("/api/groups/user-location/paging")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindGroupsByCategories(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("page", 0)
            .param("size", 10)
            .when()
            .get("/api/groups/user-categories/paging")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindCategories(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/group/categories")
            .then().log().all()
            .extract();
    }
}
