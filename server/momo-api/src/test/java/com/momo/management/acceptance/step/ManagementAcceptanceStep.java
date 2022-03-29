package com.momo.management.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.dto.GroupCreateRequest;
import com.momo.management.dto.MyGroupCardResponse;
import com.momo.management.dto.MyGroupSummaryResponse;
import com.momo.management.dto.MyPostCardResponse;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.management.dto.ParticipationGroupSummaryResponse;
import com.momo.post.dto.PostCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;

public class ManagementAcceptanceStep {

    public static void assertThatFindParticipationGroups(List<ParticipationGroupCardResponse> responses,
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

    public static void assertThatFindParticipationGroupsSummary(List<ParticipationGroupSummaryResponse> responses,
        GroupCreateRequest request) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getId()).isNotNull(),
            () -> assertThat(responses.get(0).getName()).isEqualTo(request.getName()),
            () -> assertThat(responses.get(0).getCategory()).isEqualTo(request.getCategory())
        );
    }

    public static void assertThatFindMyGroups(List<MyGroupCardResponse> responses, GroupCreateRequest request) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getId()).isNotNull(),
            () -> assertThat(responses.get(0).getName()).isEqualTo(request.getName()),
            () -> assertThat(responses.get(0).getImageUrl()).isNotNull(),
            () -> assertThat(responses.get(0).getAchievementRate()).isEqualTo(0)
        );
    }

    public static void assertThatFindMyGroupsSummary(List<MyGroupSummaryResponse> responses,
        GroupCreateRequest request) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getId()).isNotNull(),
            () -> assertThat(responses.get(0).getName()).isEqualTo(request.getName())
        );
    }

    public static void assertThatFindMyPosts(List<MyPostCardResponse> responses, PostCreateRequest request) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getGroupName()).isNotNull(),
            () -> assertThat(responses.get(0).getPostCardResponse().getId()).isNotNull(),
            () -> assertThat(responses.get(0).getPostCardResponse().getAuthorImage()).isNull(),
            () -> assertThat(responses.get(0).getPostCardResponse().getAuthorNickname()).isNotNull(),
            () -> assertThat(responses.get(0).getPostCardResponse().getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(responses.get(0).getPostCardResponse().getContents()).isEqualTo(request.getContents()),
            () -> assertThat(responses.get(0).getPostCardResponse().getCreatedDate()).isNotNull()
        );
    }

    public static ExtractableResponse<Response> requestToFindParticipationGroupCount(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/participation-group/count")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindParticipationGroups(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/participation-groups/details")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindParticipationGroupsSummary(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/participation-groups/summary")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindMyGroups(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/my-groups/details")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindMyGroupsSummary(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/my-groups/summary")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindMyPosts(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .param("page", 0)
            .param("size", 10)
            .when()
            .get("/api/management/my-posts")
            .then().log().all()
            .extract();
    }
}
