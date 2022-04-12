package com.momo.post.acceptance;

import static com.momo.CommentFixture.getCommentCreateRequest;
import static com.momo.CommentFixture.getCommentsRequest;
import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.PostFixture.getPostCreateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsNoContent;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.post.entity.PostType.NORMAL;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.assertThatCreateComment;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.assertThatFindComments;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.requestToCreateComment;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.requestTodeleteCommentById;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.requestToFindComments;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToCreatePost;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.post.dto.CommentCreateRequest;
import com.momo.post.dto.CommentResponse;
import com.momo.post.dto.CommentsResponse;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게시물 댓글 통합/인수 테스트")
public class CommentAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }

    @Test
    void 모임_참여자가_게시물에_댓글을_등록한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL)));
        CommentCreateRequest commentCreateRequest = getCommentCreateRequest(postId);

        ExtractableResponse<Response> response = requestToCreateComment(token, commentCreateRequest);

        AcceptanceStep.assertThatStatusIsCreated(response);
        assertThatCreateComment(user, commentCreateRequest, getObject(response, CommentResponse.class));
    }

    @Test
    void 댓글_작성자가_본인의_댓글을_삭제한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL)));
        Long commentId = extractId(requestToCreateComment(token, getCommentCreateRequest(postId)));

        ExtractableResponse<Response> response = requestTodeleteCommentById(token, commentId);
        assertThatStatusIsNoContent(response);
    }

    @Test
    void 모임_참여자가_게시물_댓글_목록을_조회한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL)));
        List<CommentCreateRequest> request = List.of(getCommentCreateRequest(postId), getCommentCreateRequest(postId));
        requestToCreateComment(token, request.get(0));
        requestToCreateComment(token, request.get(1));

        ExtractableResponse<Response> response = requestToFindComments(token, getCommentsRequest(postId));

        assertThatStatusIsOk(response);
        assertThatFindComments(request, getObject(response, CommentsResponse.class));
    }
}
