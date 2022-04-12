package com.momo.group.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.application.dto.response.ParticipantResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ParticipantAcceptanceStep {

    public static void assertThatFindParticipantsAfterDelete(List<ParticipantResponse> response) {
        assertThat(response.size()).isEqualTo(1);
    }

    public static ExtractableResponse<Response> requestToFindParticipants(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("groupId", groupId)
            .get("/api/group/participants")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToDeleteParticipant(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .pathParam("groupId", groupId)
            .delete("/api/group/{groupId}/participant")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToApplyParticipant(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(groupId)
            .post("/api/group/apply-participant")
            .then().log().all()
            .extract();
    }
}
