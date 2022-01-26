package com.momo.post.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.PostFixture.getNoticeCreateRequest;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.assertThatCreateComment;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.assertThatFindComments;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.requestToCreateComment;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.requestToDeleteComment;
import static com.momo.post.acceptance.step.CommentAcceptanceStep.requestToFindComments;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToCreatePost;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.dto.CommentsRequest;
import com.momo.domain.post.dto.CommentsResponse;
import com.momo.domain.post.repository.CommentRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("게시물 댓글 통합/인수 테스트")
public class CommentAcceptanceTest extends AcceptanceTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void 모임_참여자가_게시물에_댓글을_등록한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, getNoticeCreateRequest(groupId)));

        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(postId, "댓글 내용");
        ExtractableResponse<Response> response = requestToCreateComment(token, commentCreateRequest);

        AcceptanceStep.assertThatStatusIsCreated(response);
        assertThatCreateComment(getUser1(), commentCreateRequest, getObject(response, CommentResponse.class));
    }

    @Test
    public void 댓글_작성자가_본인의_댓글을_삭제한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, getNoticeCreateRequest(groupId)));

        CommentCreateRequest commentCreateRequest = new CommentCreateRequest(postId, "댓글 내용");
        Long commentId = extractId(requestToCreateComment(token, commentCreateRequest));

        ExtractableResponse<Response> response = requestToDeleteComment(token, commentId);
        AcceptanceStep.assertThatStatusIsNoContent(response);
        assertTrue(!commentRepository.existsById(commentId));
    }

    @Test
    public void 모임_참여자가_게시물_댓글_목록을_조회한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, getNoticeCreateRequest(groupId)));

        List<CommentCreateRequest> commentCreateRequests =
            List.of(new CommentCreateRequest(postId, "댓글 내용1"), new CommentCreateRequest(postId, "댓글 내용2"));
        requestToCreateComment(token, commentCreateRequests.get(0));
        requestToCreateComment(token, commentCreateRequests.get(1));

        ExtractableResponse<Response> response = requestToFindComments(token, new CommentsRequest(postId, 0, 10));
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindComments(commentCreateRequests, getObject(response, CommentsResponse.class));
    }
}
