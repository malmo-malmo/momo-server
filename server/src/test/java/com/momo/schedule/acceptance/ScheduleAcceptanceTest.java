package com.momo.schedule.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.ScheduleFixture.SCHEDULE_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.schedule.step.ScheduleAcceptanceStep.assertThatFindGroupSchedule;
import static com.momo.schedule.step.ScheduleAcceptanceStep.requestToCreateSchedule;
import static com.momo.schedule.step.ScheduleAcceptanceStep.requestToFindGroupSchedules;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.schedule.controller.dto.GroupSchedulesResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("일정 통합/인수 테스트")
public class ScheduleAcceptanceTest extends AcceptanceTest {

    @Test
    public void 모임_관리자가_해당_모임에_일정을_등록한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> response = requestToCreateSchedule(token, SCHEDULE_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatStatusIsCreated(response);
    }

    @Test
    public void 모임_관리자가_아니면_일정_등록을_실패한다() {
        String token = getAccessToken(USER1);
        String invalidToken = getAccessToken(USER2);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        ExtractableResponse<Response> res = requestToCreateSchedule(invalidToken, SCHEDULE_CREATE_REQUEST1, groupId);
        AcceptanceStep.assertThatErrorIsScheduleUnAuthorized(res);
    }

    //TODO : 출석체크 기능 개발 후 출석 여부를 확인해야한다.
    @Test
    public void 모임_참여자가_모임의_일정_목록을_조회한다() {
        String token = getAccessToken(USER1);
        Long groupId = extractId(requestToCreateGroup(token, GROUP_CREATE_REQUEST1));
        requestToCreateSchedule(token, SCHEDULE_CREATE_REQUEST1, groupId);
        ExtractableResponse<Response> response = requestToFindGroupSchedules(token, groupId);
        GroupSchedulesResponse groupSchedulesResponse = getObject(response, GroupSchedulesResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindGroupSchedule(SCHEDULE_CREATE_REQUEST1, groupSchedulesResponse, USER1, true, false, false);
    }
}
