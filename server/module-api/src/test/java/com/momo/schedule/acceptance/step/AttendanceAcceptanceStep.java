package com.momo.schedule.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceResponse;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
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

    public static ExtractableResponse<Response> requestToUpdateAttendance(String token,
        AttendanceUpdateRequests requests) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requests)
            .put("/api/attendance")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToAttendances(String token, Long scheduleId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("scheduleId", scheduleId)
            .get("/api/attendances/schedule/{scheduleId}")
            .then().log().all()
            .extract();
    }

    public static void assertThatFindAttendance(List<AttendanceCreateRequest> requests,
        List<AttendanceResponse> responses) {
        assertThat(responses.size()).isEqualTo(requests.size());

        int i = 0;
        for (AttendanceResponse attendanceResponse : responses) {
            int finalI = i;
            boolean requestAttend = requests.get(finalI).isAttend();
            Assertions.assertAll(
                () -> assertThat(attendanceResponse.getAttendanceId()).isNotNull(),
                () -> assertThat(attendanceResponse.getAttainmentRate()).isEqualTo(100),
                () -> assertThat(attendanceResponse.getUsername()).isNotNull(),
                () -> assertThat(attendanceResponse.getIsAttend()).isEqualTo(requestAttend)
            );
            i++;
        }
    }
}
