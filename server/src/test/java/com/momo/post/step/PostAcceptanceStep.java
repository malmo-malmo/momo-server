package com.momo.post.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.post.controller.dto.PostCreateRequest;
import com.momo.post.controller.dto.PostResponse;
import com.momo.user.domain.model.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class PostAcceptanceStep {

    public static void assertThatFindPost(PostCreateRequest request, PostResponse response, Long postId,
        User createUser, int commentCnt) {
        Assertions.assertAll(
            () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(response.getContents()).isEqualTo(request.getContents()),
            () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(response.getPostImages().size()).isEqualTo(response.getPostImages().size()),
            () -> assertThat(response.getPostId()).isEqualTo(postId),
            () -> assertThat(response.getUserImage()).isEqualTo(createUser.getImage()),
            () -> assertThat(response.getNickname()).isEqualTo(createUser.getNickname()),
            () -> assertThat(response.getCommentCnt()).isEqualTo(commentCnt)
        );
    }

    public static ExtractableResponse<Response> requestToCreatePost(String token, PostCreateRequest postCreateRequest,
        Long groupId) {
        postCreateRequest.setGroupId(groupId);
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(postCreateRequest)
            .when()
            .post("/api/post")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindPost(String token, Long postId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .param("postId", postId)
            .when()
            .get("/api/post")
            .then().log().all()
            .extract();
    }
}
