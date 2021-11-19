package com.momo.post.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.post.controller.dto.PostCardRequest;
import com.momo.post.controller.dto.PostCardResponse;
import com.momo.post.controller.dto.PostCreateRequest;
import com.momo.post.controller.dto.PostResponse;
import com.momo.user.domain.model.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            () -> assertThat(response.getId()).isEqualTo(postId),
            () -> assertThat(response.getAuthorImage()).isEqualTo(createUser.getImage()),
            () -> assertThat(response.getAuthorNickname()).isEqualTo(createUser.getNickname()),
            () -> assertThat(response.getCommentCnt()).isEqualTo(commentCnt)
        );
    }

    public static void assertThatFindPosts(PostCreateRequest request, List<PostCardResponse> responses,
        User createUser, int commentCnt) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(1),
            () -> assertThat(responses.get(0).getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(responses.get(0).getContents()).isEqualTo(request.getContents()),
            () -> assertThat(responses.get(0).getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(responses.get(0).getAuthorImage()).isEqualTo(createUser.getImage()),
            () -> assertThat(responses.get(0).getAuthorNickname()).isEqualTo(createUser.getNickname()),
            () -> assertThat(responses.get(0).getCommentCnt()).isEqualTo(commentCnt)
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

    public static ExtractableResponse<Response> requestToFindPosts(String token, PostCardRequest postCardRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", postCardRequest.getGroupId());
        map.put("type", postCardRequest.getType());
        map.put("page", postCardRequest.getPage());
        map.put("size", postCardRequest.getSize());
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .params(map)
            .when()
            .get("/api/posts/paging")
            .then().log().all()
            .extract();
    }
}
