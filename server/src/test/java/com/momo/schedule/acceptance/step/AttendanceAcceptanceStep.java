package com.momo.schedule.acceptance.step;

import static io.restassured.RestAssured.given;

import com.momo.schedule.controller.dto.AttendanceCreateRequests;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class AttendanceAcceptanceStep {

    public static ExtractableResponse<Response> requestToCreateAttendance(String token,
        AttendanceCreateRequests requests) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requests)
            .post("/api/attendance")
            .then().log().all()
            .extract();
    }
}
