package com.momo.group.acceptance.step;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.group.controller.dto.GroupSearchConditionRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

public class GroupAcceptanceStep {

    public static void assertThatFindGroup(GroupCreateRequest request, GroupResponse response, boolean isManager,
        boolean isParticipant, String university) {
        Assertions.assertAll(
            () -> assertThat(response.getName()).isEqualTo(request.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo(request.getImageUrl()),
            () -> assertThat(response.getStartDate()).isEqualTo(request.getStartDate()),
            () -> assertThat(response.getUniversity()).isEqualTo(university),
            () -> assertThat(response.getCity()).isEqualTo(request.getCity()),
            () -> assertThat(response.getDistrict()).isEqualTo(request.getDistrict()),
            () -> assertThat(response.isOffline()).isEqualTo(request.getIsOffline()),
            () -> assertThat(response.getIntroduction()).isEqualTo(request.getIntroduction()),
            () -> assertThat(response.getParticipantCnt()).isEqualTo(1L),
            () -> assertThat(response.getIsParticipant()).isEqualTo(isParticipant),
            () -> assertThat(response.getIsManager()).isEqualTo(isManager)
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
            .get("/api/group/{id}", groupId)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindGroupsBySearchCondition(String token,
        GroupSearchConditionRequest request) {
        Map<String, Object> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(request.getCities())) {
            map.put("cities", request.getCities());
        }
        if (!CollectionUtils.isEmpty(request.getCategories())) {
            map.put("categories", request.getCategories());
        }
        map.put("page", request.getPage());
        map.put("size", request.getSize());
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .params(map)
            .when()
            .get("/api/groups/search/paging")
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

    public static ExtractableResponse<Response> requestToFindGroupsByDistrict(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("page", 0)
            .param("size", 10)
            .when()
            .get("/api/groups/user-district/paging")
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
