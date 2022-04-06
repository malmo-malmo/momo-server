package com.momo;

import static com.momo.common.FixtureComponents.INCREASE_ID;

import com.momo.group.domain.participant.Participant;
import com.momo.group.application.dto.request.AttendanceCreateRequest;
import com.momo.group.application.dto.request.AttendanceCreateRequests;
import com.momo.group.application.dto.request.AttendanceUpdateRequest;
import com.momo.group.application.dto.request.AttendanceUpdateRequests;
import com.momo.group.domain.schedule.attendance.Attendance;
import com.momo.group.domain.schedule.Schedule;
import java.util.List;

public class AttendanceFixture {

    public static Attendance getAttendance(Schedule schedule, Participant participant, boolean isAttend) {
        return Attendance.builder()
            .schedule(schedule)
            .participant(participant)
            .isAttend(isAttend)
            .build();
    }

    public static Attendance getAttendanceWithId(Schedule schedule, Participant participant, boolean isAttend) {
        INCREASE_ID++;
        return Attendance.builder()
            .id(INCREASE_ID)
            .schedule(schedule)
            .participant(participant)
            .isAttend(isAttend)
            .build();
    }

    public static AttendanceCreateRequest getAttendanceCreateRequest(Long participantId, boolean isAttend) {
        return AttendanceCreateRequest.builder()
            .participantId(participantId)
            .isAttend(isAttend)
            .build();
    }

    public static AttendanceCreateRequests getAttendanceCreateRequests(Long scheduleId,
        List<AttendanceCreateRequest> requests) {
        return AttendanceCreateRequests.builder()
            .scheduleId(scheduleId)
            .attendanceCreateRequests(requests)
            .build();
    }

    public static AttendanceUpdateRequests getAttendanceUpdateRequests(Long scheduleId,
        List<AttendanceUpdateRequest> requests) {
        return AttendanceUpdateRequests.builder()
            .scheduleId(scheduleId)
            .attendanceUpdateRequests(requests)
            .build();
    }

    public static AttendanceUpdateRequest getAttendanceUpdateRequest(Long attendanceId, boolean isAttend) {
        return AttendanceUpdateRequest.builder()
            .attendanceId(attendanceId)
            .isAttend(isAttend)
            .build();
    }
}
