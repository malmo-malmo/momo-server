package com.momo.post.acceptance;

import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.PostFixture.getPostCardsRequest;
import static com.momo.PostFixture.getPostCreateRequest;
import static com.momo.PostFixture.getPostUpdateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.common.exception.ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.post.entity.PostType.NORMAL;
import static com.momo.post.entity.PostType.NOTICE;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.post.acceptance.step.PostAcceptanceStep.assertThatFindPost;
import static com.momo.post.acceptance.step.PostAcceptanceStep.assertThatFindPosts;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToCreatePost;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToDeletePost;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToFindPost;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToFindPosts;
import static com.momo.post.acceptance.step.PostAcceptanceStep.requestToUpdatePost;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.exception.ErrorCode;
import com.momo.post.dto.PostCardResponse;
import com.momo.post.dto.PostCardsRequest;
import com.momo.post.dto.PostCreateRequest;
import com.momo.post.dto.PostResponse;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게시물 통합/인수 테스트")
public class PostAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }

    @Test
    void 모임에_게시물을_등록한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));

        ExtractableResponse<Response> response = requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL));

        AcceptanceStep.assertThatStatusIsCreated(response);
    }

    @Test
    void 모임에_게시물을_등록할_때_모임의_참여자가_아니면_실패한다() {
        String token1 = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token1, getGroupCreateRequest(LIFE, true)));
        String token2 = getAccessToken(getUser());

        ExtractableResponse<Response> response = requestToCreatePost(token2, getPostCreateRequest(groupId, NORMAL));

        assertThatCustomException(response, GROUP_PARTICIPANT_UNAUTHORIZED);
    }

    @Test
    void 모임에_공지사항을_등록한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));

        ExtractableResponse<Response> response = requestToCreatePost(token, getPostCreateRequest(groupId, NOTICE));

        AcceptanceStep.assertThatStatusIsCreated(response);
    }

    @Test
    void 모임에_게시물을_수정한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL)));

        ExtractableResponse<Response> response = requestToUpdatePost(token, getPostUpdateRequest(postId));

        assertThatStatusIsOk(response);
    }

    @Test
    void 모임에_게시물을_삭제한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        Long postId = extractId(requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL)));

        ExtractableResponse<Response> response = requestToDeletePost(token, postId);

        AcceptanceStep.assertThatStatusIsNoContent(response);
    }

    @Test
    void 모임에_공지사항을_등록할_때_모임의_관리자가_아니면_실패한다() {
        String token1 = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token1, getGroupCreateRequest(LIFE, true)));
        String token2 = getAccessToken(getUser());

        ExtractableResponse<Response> response = requestToCreatePost(token2, getPostCreateRequest(groupId, NOTICE));

        assertThatCustomException(response, ErrorCode.GROUP_MANAGER_AUTHORIZED);
    }

    @Test
    void 모임_게시물_또는_공지사항을_상세_조회한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        PostCreateRequest postCreateRequest = getPostCreateRequest(groupId, NORMAL);
        Long postId = extractId(requestToCreatePost(token, postCreateRequest));

        ExtractableResponse<Response> response = requestToFindPost(token, postId);

        assertThatStatusIsOk(response);
        assertThatFindPost(postCreateRequest, getObject(response, PostResponse.class), postId, user);
    }

    @Test
    void 모임_게시물_또는_공지사항을_상세_조회할_때_참여자가_아니면_실패한다() {
        String token1 = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token1, getGroupCreateRequest(LIFE, true)));
        Long postId = extractId(requestToCreatePost(token1, getPostCreateRequest(groupId, NORMAL)));
        String token2 = getAccessToken(getUser());

        ExtractableResponse<Response> response = requestToFindPost(token2, postId);
        assertThatCustomException(response, GROUP_PARTICIPANT_UNAUTHORIZED);
    }

    @Test
    void 게시물_목록을_조회한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));
        requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL));
        requestToCreatePost(token, getPostCreateRequest(groupId, NORMAL));
        PostCardsRequest postCardsRequest = getPostCardsRequest(groupId, NORMAL);

        ExtractableResponse<Response> response = requestToFindPosts(token, postCardsRequest);

        assertThatStatusIsOk(response);
        assertThatFindPosts(getObjects(response, PostCardResponse.class), user, 0);
    }

    @Test
    void 모임_참여자가_아니면_게시물_또는_공지사항_목록_조회를_실패한다() {
        String token1 = getAccessToken(user);
        String token2 = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(token1, getGroupCreateRequest(LIFE, true)));
        requestToCreatePost(token1, getPostCreateRequest(groupId, NORMAL));
        PostCardsRequest postCardsRequest = getPostCardsRequest(groupId, NORMAL);

        ExtractableResponse<Response> response = requestToFindPosts(token2, postCardsRequest);
        assertThatCustomException(response, GROUP_PARTICIPANT_UNAUTHORIZED);
    }
}
