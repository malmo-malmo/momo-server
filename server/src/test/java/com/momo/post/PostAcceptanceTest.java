package com.momo.post;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.PostFixture.NOTICE_CREATE_REQUEST1;
import static com.momo.fixture.PostFixture.POST_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;
import static com.momo.post.step.PostAcceptanceStep.requestToCreate;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게시글 통합/인수 테스트")
public class PostAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("모임에 게시물을 등록한다.")
    public void createPost_success() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(GroupAcceptanceStep.requestToCreate(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = requestToCreate(token, POST_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatStatusIsCreated(res);
    }

    @Test
    @DisplayName("모임에 게시물을 등록할 때 모임의 참여자가 아니면 실패한다.")
    public void createPost_fail() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(GroupAcceptanceStep.requestToCreate(token, GROUP_CREATE_REQUEST1));

        String invalidToken = getAccessToken(USER2);
        ExtractableResponse<Response> res = requestToCreate(invalidToken, POST_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatErrorIsParticipantUnAuthorized(res);
    }

    @Test
    @DisplayName("모임에 공지사항을 등록한다.")
    public void createNotice_success() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(GroupAcceptanceStep.requestToCreate(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = requestToCreate(token, NOTICE_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatStatusIsCreated(res);
    }

    @Test
    @DisplayName("모임에 공지사항을 등록할 때 모임의 관리자가 아니면 실패한다.")
    public void createNotice_fail() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(GroupAcceptanceStep.requestToCreate(token, GROUP_CREATE_REQUEST1));

        String invalidToken = getAccessToken(USER2);
        ExtractableResponse<Response> res = requestToCreate(invalidToken, NOTICE_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatErrorIsNoticeUnAuthorized(res);
    }
}
