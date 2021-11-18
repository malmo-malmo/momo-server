package com.momo.post.step;

import static io.restassured.RestAssured.given;

import com.momo.post.controller.dto.PostCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class PostAcceptanceStep {

    public static ExtractableResponse<Response> requestToCreate(String token, PostCreateRequest postCreateRequest,
        Long groupId) {
        postCreateRequest.setGroupId(groupId);
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(postCreateRequest)
            .when()
            .post("/api/post")
            .then().log().all()
            .extract();
    }

}
