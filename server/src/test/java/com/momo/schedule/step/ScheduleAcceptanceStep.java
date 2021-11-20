package com.momo.schedule.step;

import static io.restassured.RestAssured.given;

import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ScheduleAcceptanceStep {

    public static ExtractableResponse<Response> requestToCreateSchedule(String token, ScheduleCreateRequest request,
        Long groupId) {
        request.setGroupId(groupId);
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/api/schedule")
            .then().log().all()
            .extract();
    }
}
