package com.momo.post.acceptance;

import static com.momo.common.CommonFileUploadSupport.uploadTestFile;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.domain.common.exception.ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.PostFixture.getNoticeCreateRequest;
import static com.momo.fixture.PostFixture.getPostCreateRequest;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.fixture.UserFixture.getUser2;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToCreatePost;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToDeletePost;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToFindPost;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToFindPosts;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToUpdatePost;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.PostCardsRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.dto.PostUpdateRequest;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.repository.PostRepository;
import com.momo.post.acceptance.step.PostAcceptanceStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("게시물 통합/인수 테스트")
public class PostAcceptanceTest extends AcceptanceTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void 모임에_게시물을_등록한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = requestToCreatePost(token, getPostCreateRequest(groupId));
        AcceptanceStep.assertThatStatusIsCreated(res);
    }

    @Test
    public void 모임에_게시물을_등록할_때_모임의_참여자가_아니면_실패한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        String invalidToken = getAccessToken(getUser2());
        ExtractableResponse<Response> response = requestToCreatePost(invalidToken, getPostCreateRequest(groupId));
        assertThatCustomException(response, GROUP_PARTICIPANT_UNAUTHORIZED);
    }

    @Test
    public void 모임에_공지사항을_등록한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToCreatePost(token, getNoticeCreateRequest(groupId));
        AcceptanceStep.assertThatStatusIsCreated(response);
    }

    @Test
    public void 모임에_게시물을_수정한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId)));
        PostUpdateRequest request = PostUpdateRequest.builder()
            .postId(postId)
            .title("수정된 게시글 제목")
            .content("수정된 게시글 내용")
            .images(List.of(uploadTestFile))
            .build();
        ExtractableResponse<Response> response = requestToUpdatePost(token, request);
        AcceptanceStep.assertThatStatusIsOk(response);

        Post post = postRepository.findById(postId).get();
        PostAcceptanceStep.assertThatUpdatePost(request, post);
    }

    @Test
    public void 모임에_게시물을_삭제한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId)));
        ExtractableResponse<Response> response = requestToDeletePost(token, postId);
        AcceptanceStep.assertThatStatusIsNoContent(response);
        assertTrue(!postRepository.existsById(postId));
    }

    @Test
    public void 모임에_공지사항을_등록할_때_모임의_관리자가_아니면_실패한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        String invalidToken = getAccessToken(getUser2());
        ExtractableResponse<Response> response = requestToCreatePost(invalidToken, getNoticeCreateRequest(groupId));
        assertThatCustomException(response, ErrorCode.GROUP_MANAGER_AUTHORIZED);
    }

    @Test
    public void 모임_게시물_또는_공지사항을_상세_조회한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId)));
        ExtractableResponse<Response> response = requestToFindPost(token, postId);
        PostResponse postResponse = getObject(response, PostResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        PostAcceptanceStep.assertThatFindPost(getPostCreateRequest(groupId), postResponse, postId, getUser1());
    }

    @Test
    public void 모임_게시물_또는_공지사항을_상세_조회할_때_참여자가_아니면_실패한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId)));
        String invalidToken = getAccessToken(getUser2());
        ExtractableResponse<Response> response = requestToFindPost(invalidToken, postId);
        assertThatCustomException(response, GROUP_PARTICIPANT_UNAUTHORIZED);
    }

    @Test
    public void 게시물_목록을_조회한다() {
        String token = getAccessToken(getUser1());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        requestToCreatePost(token, getPostCreateRequest(groupId));
        requestToCreatePost(token, getNoticeCreateRequest(groupId));
        PostCardsRequest postCardsRequest = new PostCardsRequest(groupId, PostType.NORMAL.name(), 0, 10);
        ExtractableResponse<Response> response = requestToFindPosts(token, postCardsRequest);
        List<PostCardResponse> postCardResponses = getObjects(response, PostCardResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        PostAcceptanceStep.assertThatFindPosts(getPostCreateRequest(groupId), postCardResponses, getUser1(), 0);
    }

    @Test
    public void 모임_참여자가_아니면_게시물_또는_공지사항_목록_조회를_실패한다() {
        String token = getAccessToken(getUser1());
        String invalidToken = getAccessToken(getUser2());
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        requestToCreatePost(token, getPostCreateRequest(groupId));
        PostCardsRequest postCardsRequest = new PostCardsRequest(groupId, PostType.NOTICE.name(), 0, 10);
        ExtractableResponse<Response> response = requestToFindPosts(invalidToken, postCardsRequest);
        assertThatCustomException(response, GROUP_PARTICIPANT_UNAUTHORIZED);
    }
}
