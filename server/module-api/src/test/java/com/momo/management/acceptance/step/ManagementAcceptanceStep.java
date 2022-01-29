package com.momo.management.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipationGroupCardResponse;
import com.momo.domain.post.dto.PostCreateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;

public class ManagementAcceptanceStep {

    public static void assertThatfindParticipationGroups(List<ParticipationGroupCardResponse> responses,
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

    public static void assertThatFindMyPosts(List<MyPostCardResponse> responses, PostCreateRequest request,
        String groupName) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getGroupName()).isEqualTo(groupName),
            () -> assertThat(responses.get(0).getPostCardResponse().getId()).isNotNull(),
            () -> assertThat(responses.get(0).getPostCardResponse().getAuthorImage()).isNull(),
            () -> assertThat(responses.get(0).getPostCardResponse().getAuthorNickname()).isNotNull(),
            () -> assertThat(responses.get(0).getPostCardResponse().getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(responses.get(0).getPostCardResponse().getContents()).isEqualTo(request.getContents()),
            () -> assertThat(responses.get(0).getPostCardResponse().getCreatedDate()).isNotNull()
        );
    }

    public static ExtractableResponse<Response> requestTofindParticipationGroupCount(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/group/participation/count")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestTofindParticipationGroups(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/management/groups/participation")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindMyPosts(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .param("page", 0)
            .param("size", 10)
            .when()
            .get("/api/management/posts")
            .then().log().all()
            .extract();
    }
}
