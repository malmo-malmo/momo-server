package com.momo.common.acceptance.step;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.exception.ErrorCode;
import com.momo.common.exception.ErrorResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

public class AcceptanceStep {

    public static void assertThatStatusIsOk(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void assertThatStatusIsCreated(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void assertThatStatusIsBadRequest(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static void assertThatErrorIsParticipantUnAuthorized(ExtractableResponse<Response> response) {
        assertThat(response.body().as(ErrorResponse.class).getMessage()).isEqualTo(
            ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    public static void assertThatErrorIsNoticeUnAuthorized(ExtractableResponse<Response> response) {
        assertThat(response.body().as(ErrorResponse.class).getMessage()).isEqualTo(
            ErrorCode.GROUP_NOTICE_UNAUTHORIZED.getMessage());
    }

    public static void assertThatErrorIsScheduleUnAuthorized(ExtractableResponse<Response> response) {
        assertThat(response.body().as(ErrorResponse.class).getMessage()).isEqualTo(
            ErrorCode.GROUP_SCHEDULE_UNAUTHORIZED.getMessage());
    }

    public static void assertThatErrorIsParticipantsUnAuthorized(ExtractableResponse<Response> response) {
        assertThat(response.body().as(ErrorResponse.class).getMessage()).isEqualTo(
            ErrorCode.GROUP_PARTICIPANTS_UNAUTHORIZED.getMessage());
    }
}
