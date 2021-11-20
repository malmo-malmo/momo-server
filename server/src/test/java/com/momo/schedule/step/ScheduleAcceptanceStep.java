package com.momo.schedule.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.schedule.controller.dto.GroupSchedulesResponse;
import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import com.momo.user.domain.model.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ScheduleAcceptanceStep {

    public static void assertThatFindGroupSchedule(ScheduleCreateRequest request, GroupSchedulesResponse response,
        User author, boolean isManager, boolean isAttendance, boolean isAttendanceCheck) {
        Assertions.assertAll(
            () -> assertThat(response.getSchedules().size()).isEqualTo(1),
            () -> assertThat(response.isManager()).isEqualTo(isManager),
            () -> assertThat(response.getSchedules().get(0).getAuthorImage()).isEqualTo(author.getImage()),
            () -> assertThat(response.getSchedules().get(0).getAuthorNickname()).isEqualTo(author.getNickname()),
            () -> assertThat(response.getSchedules().get(0).getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(response.getSchedules().get(0).getStartDateTime()).isEqualTo(request.getStartDateTime()),
            () -> assertThat(response.getSchedules().get(0).getContents()).isEqualTo(request.getContents()),
            () -> assertThat(response.getSchedules().get(0).isOffline()).isEqualTo(request.getIsOffline()),
            () -> assertThat(response.getSchedules().get(0).isAttendance()).isEqualTo(isAttendance),
            () -> assertThat(response.getSchedules().get(0).isAttendanceCheck()).isEqualTo(isAttendanceCheck)
        );
    }

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

    public static ExtractableResponse<Response> requestToFindGroupSchedules(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("groupId", groupId)
            .param("page", 0)
            .param("size", 10)
            .get("/api/schedules/group")
            .then().log().all()
            .extract();
    }
}
