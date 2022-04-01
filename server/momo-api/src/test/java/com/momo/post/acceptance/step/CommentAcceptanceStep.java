package com.momo.post.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.post.dto.CommentCreateRequest;
import com.momo.post.dto.CommentResponse;
import com.momo.post.dto.CommentsRequest;
import com.momo.post.dto.CommentsResponse;
import com.momo.user.domain.model.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CommentAcceptanceStep {

    public static void assertThatCreateComment(User user, CommentCreateRequest request, CommentResponse response) {
        Assertions.assertAll(
            () -> assertThat(response.getAuthorImage()).isEqualTo(user.getImageUrl()),
            () -> assertThat(response.getAuthorNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(response.getContents()).isEqualTo(request.getContents())
        );
    }

    public static void assertThatFindComments(List<CommentCreateRequest> requests, CommentsResponse response) {
        Assertions.assertAll(
            () -> assertThat(response.getCommentResponses().size()).isEqualTo(2),
            () -> assertThat(response.getCommentCnt()).isEqualTo(2),
            () -> assertThat(response.getCommentResponses()).extracting("authorId").isNotNull(),
            () -> assertThat(response.getCommentResponses()).extracting("contents")
                .containsExactly(requests.get(1).getContents(), requests.get(0).getContents()),
            () -> assertThat(response.getCommentResponses()).extracting("createdDate").isNotNull()
        );
    }

    public static ExtractableResponse<Response> requestToCreateComment(String token, CommentCreateRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .post("/api/comment")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindComments(String token, CommentsRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .param("postId", request.getPostId())
            .param("size", request.getSize())
            .get("/api/comments/paging")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestTodeleteCommentById(String token, Long commentId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .pathParam("commentId", commentId)
            .delete("/api/comment/{commentId}")
            .then().log().all()
            .extract();
    }
}
