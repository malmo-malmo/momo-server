package com.momo.post.acceptance.step;

import static com.momo.common.CommonFileUploadSupport.uploadAssuredSupport;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.application.dto.response.PostCardResponse;
import com.momo.group.application.dto.request.PostCardsRequest;
import com.momo.group.application.dto.request.PostCreateRequest;
import com.momo.group.application.dto.response.PostResponse;
import com.momo.group.application.dto.request.PostUpdateRequest;
import com.momo.group.domain.post.Post;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class PostAcceptanceStep {

    public static void assertThatFindPost(PostCreateRequest request, PostResponse response, Long postId,
        User createUser) {
        Assertions.assertAll(
            () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(response.getContents()).isEqualTo(request.getContents()),
            () -> assertThat(response.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(response.getImageUrls().size()).isEqualTo(1),
            () -> assertThat(response.getId()).isEqualTo(postId),
            () -> assertThat(response.getAuthorImage()).isEqualTo(createUser.getImageUrl()),
            () -> assertThat(response.getAuthorNickname()).isEqualTo(createUser.getNickname()),
            () -> assertThat(response.getAuthorId()).isNotNull(),
            () -> assertThat(response.getCreatedDate()).isNotNull()
        );
    }

    public static void assertThatUpdatePost(PostUpdateRequest request, Post post) {
        Assertions.assertAll(
            () -> assertThat(post).isNotNull(),
            () -> assertThat(post.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(post.getContents()).isEqualTo(request.getContents())
        );
    }

    public static void assertThatFindPosts(List<PostCardResponse> responses, User author, int commentCnt) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(2),
            () -> assertThat(responses.get(0).getId()).isNotNull(),
            () -> assertThat(responses.get(0).getAuthorNickname()).isEqualTo(author.getNickname()),
            () -> assertThat(responses.get(0).getCommentCnt()).isEqualTo(commentCnt),
            () -> assertThat(responses.get(1).getId()).isNotNull(),
            () -> assertThat(responses.get(1).getAuthorNickname()).isEqualTo(author.getNickname()),
            () -> assertThat(responses.get(1).getCommentCnt()).isEqualTo(commentCnt)
        );
    }

    public static ExtractableResponse<Response> requestToCreatePost(String token, PostCreateRequest postCreateRequest) {
        return uploadAssuredSupport(given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE), postCreateRequest)
            .when()
            .post("/api/post")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdatePost(String token, PostUpdateRequest request) {
        return uploadAssuredSupport(given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            .when(), request)
            .put("/api/post")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToDeletePost(String token, Long postId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .pathParam("postId", postId)
            .when()
            .delete("/api/post/{postId}")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindPost(String token, Long postId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/post/{id}", postId)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindPosts(String token, PostCardsRequest postCardsRequest) {
        return uploadAssuredSupport(
            given().log().all()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when(), postCardsRequest)
            .get("/api/posts/paging")
            .then().log().all()
            .extract();
    }
}
