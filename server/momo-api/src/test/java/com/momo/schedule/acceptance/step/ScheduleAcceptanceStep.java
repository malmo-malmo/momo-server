package com.momo.schedule.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.dto.UpcomingScheduleResponse;
import com.momo.domain.schedule.dto.UserScheduleResponse;
import com.momo.domain.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ScheduleAcceptanceStep {

    public static void assertThatFindGroupSchedule(ScheduleCreateRequest request, GroupScheduleResponses response,
        User author, boolean isAttend, boolean attendanceCheck) {
        Assertions.assertAll(
            () -> assertThat(response.getGroupScheduleResponses().size()).isEqualTo(1),
            () -> assertThat(response.getManagerId()).isNotNull(),
            () -> assertThat(response.getGroupScheduleResponses().get(0).getAuthorImage())
                .isEqualTo(author.getImageUrl()),
            () -> assertThat(response.getGroupScheduleResponses().get(0).getAuthorNickname())
                .isEqualTo(author.getNickname()),
            () -> assertThat(response.getGroupScheduleResponses().get(0).getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(response.getGroupScheduleResponses().get(0).getStartDateTime())
                .isEqualTo(request.getStartDateTime()),
            () -> assertThat(response.getGroupScheduleResponses().get(0).getContents())
                .isEqualTo(request.getContents()),
            () -> assertThat(response.getGroupScheduleResponses().get(0).isOffline()).isEqualTo(request.getIsOffline()),
            () -> assertThat(response.getGroupScheduleResponses().get(0).isAttend()).isEqualTo(isAttend),
            () -> assertThat(response.getGroupScheduleResponses().get(0).isAttendanceCheck()).isEqualTo(attendanceCheck)
        );
    }

    public static void assertThatFindUserSchedules(List<UserScheduleResponse> response) {
        assertThat(response.size()).isEqualTo(1);
    }

    public static void assertThatFindUpcomingSchedule(UpcomingScheduleResponse response,
        ScheduleCreateRequest request) {
        Assertions.assertAll(
            () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(response.getStartDateTime().getMonth()).isEqualTo(request.getStartDateTime().getMonth()),
            () -> assertThat(response.getStartDateTime().getHour()).isEqualTo(request.getStartDateTime().getHour()),
            () -> assertThat(response.getStartDateTime().getMinute()).isEqualTo(request.getStartDateTime().getMinute()),
            () -> assertThat(response.getStartDateTime().getDayOfMonth())
                .isEqualTo(request.getStartDateTime().getDayOfMonth())
        );
    }

    public static ExtractableResponse<Response> requestToCreateSchedule(String token, ScheduleCreateRequest request) {
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
            .param("groupId", groupId)
            .param("page", 0)
            .param("size", 10)
            .get("/api/schedule/group-schedules")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindUserSchedules(String token, String searchStartDate,
        String searchEndDate) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .param("searchStartDate", searchStartDate)
            .param("searchEndDate", searchEndDate)
            .get("/api/schedule/user-schedules")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindUpcomingSchedule(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .param("groupId", groupId)
            .get("/api/schedule/upcoming")
            .then().log().all()
            .extract();
    }
}
