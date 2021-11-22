package com.momo.group.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToFindParticipants;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("모임 참여자 통합/인수 테스트")
public class ParticipantAcceptanceTest extends AcceptanceTest {

    //TODO : 출석체크 기능 완료되면 유저별 출석한 일정 수, 모임 일정 수를 확인하는 테스트 코드 필요
    @Test
    public void 모임_관리자가_참여자를_조회한다() {
        String managerToken = getAccessToken(USER1);
        String participantToken = getAccessToken(USER2);
        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));
        requestToApplyParticipant(participantToken, groupId);
        ExtractableResponse<Response> response = requestToFindParticipants(managerToken, groupId);
        AcceptanceStep.assertThatStatusIsOk(response);
    }

    @Test
    public void 모임_관리자가_아닌_유저가_참여자를_조회하면_실패한다() {
        String managerToken = getAccessToken(USER1);
        String participantToken = getAccessToken(USER2);
        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));
        requestToApplyParticipant(participantToken, groupId);
        ExtractableResponse<Response> response = requestToFindParticipants(participantToken, groupId);
        AcceptanceStep.assertThatErrorIsParticipantsUnAuthorized(response);
    }
}