package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UniversityAcceptanceStep {

    public static ExtractableResponse<Response> requestToFindUniversities(String token, String universityName) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("universityName", universityName)
            .when()
            .get("/api/universities")
            .then().log().all()
            .extract();
    }
}
