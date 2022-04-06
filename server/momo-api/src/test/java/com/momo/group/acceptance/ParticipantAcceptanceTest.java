package com.momo.group.acceptance;

import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatCustomException;
import static com.momo.common.exception.ErrorCode.GROUP_MANAGER_WITHDRAW_NOT_ALLOW;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.assertThatFindParticipantsAfterDelete;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToDeleteParticipant;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToFindParticipants;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.dto.response.ParticipantResponse;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("모임 참여자 통합/인수 테스트")
public class ParticipantAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }

    /**
     * TODO : 출석체크 기능 완료되면 유저별 출석한 일정 수, 모임 일정 수를 확인하는 테스트 코드 필요
     */
    @Test
    void 모임_관리자가_참여자_목록을_조회한다() {
        String managerToken = getAccessToken(user);
        String participantToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, true)));
        requestToApplyParticipant(participantToken, groupId);

        ExtractableResponse<Response> response = requestToFindParticipants(managerToken, groupId);

        AcceptanceStep.assertThatStatusIsOk(response);
    }

    @Test
    void 모임_관리자가_아닌_유저가_참여자_목록을_조회하면_실패한다() {
        String managerToken = getAccessToken(user);
        String participantToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, true)));
        requestToApplyParticipant(participantToken, groupId);

        ExtractableResponse<Response> response = requestToFindParticipants(participantToken, groupId);

        assertThatCustomException(response, ErrorCode.GROUP_MANAGER_AUTHORIZED);
    }

    @Test
    void 모임_참여자가_모임에서_탈퇴한다() {
        String managerToken = getAccessToken(user);
        String participantToken = getAccessToken(getUser());
        Long groupId = extractId(requestToCreateGroup(managerToken, getGroupCreateRequest(LIFE, true)));
        requestToApplyParticipant(participantToken, groupId);

        ExtractableResponse<Response> response = requestToDeleteParticipant(participantToken, groupId);
        AcceptanceStep.assertThatStatusIsNoContent(response);

        assertThatFindParticipantsAfterDelete(
            getObjects(requestToFindParticipants(managerToken, groupId), ParticipantResponse.class)
        );
    }

    @Test
    void 모임_관리자가_모임에서_탈퇴하면_실패한다() {
        String token = getAccessToken(user);
        Long groupId = extractId(requestToCreateGroup(token, getGroupCreateRequest(LIFE, true)));

        ExtractableResponse<Response> response = requestToDeleteParticipant(token, groupId);

        assertThatCustomException(response, GROUP_MANAGER_WITHDRAW_NOT_ALLOW);
    }
}
