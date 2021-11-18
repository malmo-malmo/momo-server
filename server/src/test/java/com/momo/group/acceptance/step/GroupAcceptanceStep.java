package com.momo.group.acceptance.step;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.GroupCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GroupAcceptanceStep {

    public static void assertThatFindCategory(List<EnumResponse> responses) {
        assertThat(EnumResponse.listOfCategory().size()).isEqualTo(responses.size());
    }

    public static ExtractableResponse<Response> requestToCreate(String token,
        GroupCreateRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/api/group")
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
