package com.momo.post;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.PostFixture.NOTICE_CREATE_REQUEST1;
import static com.momo.fixture.PostFixture.POST_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.post.step.PostAcceptanceStep.requestToCreatePost;
import static com.momo.post.step.PostAcceptanceStep.requestToFindPost;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.post.controller.dto.PostResponse;
import com.momo.post.step.PostAcceptanceStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게시글 통합/인수 테스트")
public class PostAcceptanceTest extends AcceptanceTest {

    @Test
    public void 모임에_게시물을_등록한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = requestToCreatePost(token, POST_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatStatusIsCreated(res);
    }

    @Test
    public void 모임에_게시물을_등록할_때_모임의_참여자가_아니면_실패한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        String invalidToken = getAccessToken(USER2);
        ExtractableResponse<Response> res = requestToCreatePost(invalidToken, POST_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatErrorIsParticipantUnAuthorized(res);
    }

    @Test
    public void 모임에_공지사항을_등록한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToCreatePost(token, NOTICE_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatStatusIsCreated(response);
    }

    @Test
    public void 모임에_공지사항을_등록할_때_모임의_관리자가_아니면_실패한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        String invalidToken = getAccessToken(USER2);
        ExtractableResponse<Response> response = requestToCreatePost(invalidToken, NOTICE_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatErrorIsNoticeUnAuthorized(response);
    }

    @Test
    public void 모임_게시물_또는_공지사항을_상세_조회한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, POST_CREATE_REQUEST1, groupId));
        ExtractableResponse<Response> response = requestToFindPost(token, postId);
        PostResponse postResponse = getObject(response, PostResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        PostAcceptanceStep.assertThatFindPost(POST_CREATE_REQUEST1, postResponse, postId, USER1, 0);
    }

    @Test
    public void 모임_게시물_또는_공지사항을_상세_조회할_때_참여자가_아니면_실패한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        Long postId = extractId(requestToCreatePost(token, POST_CREATE_REQUEST1, groupId));
        String invalidToken = getAccessToken(USER2);
        ExtractableResponse<Response> response = requestToFindPost(invalidToken, postId);
        AcceptanceStep.assertThatErrorIsParticipantUnAuthorized(response);
    }
}
