package com.momo.schedule.acceptance;

import static com.momo.fixture.AttendanceFixture.getAttendanceCreateRequest;
import static com.momo.fixture.AttendanceFixture.getAttendanceCreateRequests;
import static com.momo.fixture.GroupFixture.GROUP_CREATE_REQUEST1;
import static com.momo.fixture.ScheduleFixture.getScheduleCreateRequest1;
import static com.momo.fixture.UserFixture.getUser1;
import static com.momo.fixture.UserFixture.getUser2;
import static com.momo.fixture.UserFixture.getUser3;
import static com.momo.group.acceptance.step.GroupAcceptanceStep.requestToCreateGroup;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToApplyParticipant;
import static com.momo.group.acceptance.step.ParticipantAcceptanceStep.requestToFindParticipants;
import static com.momo.schedule.acceptance.step.AttendanceAcceptanceStep.requestToCreateAttendance;
import static com.momo.schedule.acceptance.step.ScheduleAcceptanceStep.requestToCreateSchedule;
import static com.momo.user.acceptance.step.UserAcceptanceStep.requestToFindMyInformation;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.schedule.controller.dto.AttendanceCreateRequests;
import com.momo.user.controller.dto.UserResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AttendanceAcceptanceTest extends AcceptanceTest {

    @Test
    void 모임_관리자가_일정_출석_체크를_한다() {
        String managerToken = getAccessToken(getUser1());
        String userToken1 = getAccessToken(getUser2());
        String userToken2 = getAccessToken(getUser3());

        Long groupId = extractId(requestToCreateGroup(managerToken, GROUP_CREATE_REQUEST1));

        requestToApplyParticipant(userToken1, groupId);
        requestToApplyParticipant(userToken2, groupId);

        Long scheduleId = extractId(requestToCreateSchedule(managerToken, getScheduleCreateRequest1(groupId)));

        Long managerId = getObject(requestToFindMyInformation(managerToken), UserResponse.class).getId();
        Long userId1 = getObject(requestToFindMyInformation(userToken1), UserResponse.class).getId();
        Long userId2 = getObject(requestToFindMyInformation(userToken2), UserResponse.class).getId();

        AttendanceCreateRequests attendanceCreateRequests = getAttendanceCreateRequests(
            groupId,
            scheduleId,
            List.of(getAttendanceCreateRequest(managerId, true),
                getAttendanceCreateRequest(userId1, true),
                getAttendanceCreateRequest(userId2, false)
            )
        );

        requestToCreateAttendance(managerToken, attendanceCreateRequests);
        requestToFindParticipants(managerToken, groupId);
    }
}
