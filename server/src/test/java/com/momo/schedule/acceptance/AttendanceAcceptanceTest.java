package com.momo.schedule.acceptance;

import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.ScheduleFixture.SCHEDULE_CREATE_REQUEST1;
import static com.momo.fixture.UserFixture.USER1;
import static com.momo.fixture.UserFixture.USER2;
import static com.momo.fixture.UserFixture.USER3;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToFindParticipants;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToCreateAttendance;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToCreateSchedule;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.schedule.controller.dto.AttendanceCreateRequest;
import com.momo.schedule.controller.dto.AttendanceCreateRequests;
import com.momo.user.acceptance.step.UserAcceptanceStep;
import com.momo.user.controller.dto.UserResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AttendanceAcceptanceTest extends AcceptanceTest {

    @Test
    void 모임_관리자가_일정_출석_체크를_한다() {
        String managerToken = getAccessToken(USER1);
        String participantToken1 = getAccessToken(USER2);
        String participantToken2 = getAccessToken(USER3);

        Long id0 = getObject(requestToFindMyInformation(managerToken), UserResponse.class).getId();
        Long id1 = getObject(requestToFindMyInformation(participantToken1), UserResponse.class).getId();
        Long id2 = getObject(requestToFindMyInformation(participantToken1), UserResponse.class).getId();

        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));

        requestToApplyParticipant(participantToken1, groupId);
        requestToApplyParticipant(participantToken2, groupId);

        Long scheduleId = extractId(requestToCreateSchedule(managerToken, SCHEDULE_CREATE_REQUEST1, groupId));

        AttendanceCreateRequest request0 = new AttendanceCreateRequest(id0, true);
        AttendanceCreateRequest request1 = new AttendanceCreateRequest(id1, true);
        AttendanceCreateRequest request2 = new AttendanceCreateRequest(id2, false);
        List<AttendanceCreateRequest> requests = List.of(request0, request1, request2);

        AttendanceCreateRequests requests1 = new AttendanceCreateRequests(groupId, scheduleId, requests);
        requestToCreateAttendance(managerToken, requests1);

        requestToFindParticipants(managerToken, groupId);
    }
}
