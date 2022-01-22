package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;

public class ManagementAcceptanceStep {

    public static void assertThatFindParticipatingGroups(List<ParticipatingGroupCardResponse> responses,
        GroupCreateRequest request) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getId()).isNotNull(),
            () -> assertThat(responses.get(0).getImageUrl()).isNotNull(),
            () -> assertThat(responses.get(0).getName()).isEqualTo(request.getName()),
            () -> assertThat(responses.get(0).getStartDate()).isEqualTo(request.getStartDate()),
            () -> assertThat(responses.get(0).isOffline()).isEqualTo(request.getIsOffline()),
            () -> assertThat(responses.get(0).isEnd()).isFalse(),
            () -> assertThat(responses.get(0).getParticipantCnt()).isEqualTo(1)
        );
    }

    public static ExtractableResponse<Response> requestToFindParticipatingGroupCount(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/group/participation/count")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindParticipatingGroups(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/groups/participation")
            .then().log().all()
            .extract();
    }
}
