package com.momo.fixture;

import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceUpdateRequest;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import java.util.List;

public class AttendanceFixture {

    public static AttendanceCreateRequest getAttendanceCreateRequest(Long userId, boolean isAttend) {
        return AttendanceCreateRequest.builder()
            .userId(userId)
            .isAttend(isAttend)
            .build();
    }

    public static AttendanceCreateRequests getAttendanceCreateRequests(Long groupId, Long scheduleId,
        List<AttendanceCreateRequest> requests) {
        return AttendanceCreateRequests.builder()
            .groupId(groupId)
            .scheduleId(scheduleId)
            .attendanceCreateRequests(requests)
            .build();
    }

    public static AttendanceUpdateRequests getAttendanceUpdateRequests(Long groupId, Long scheduleId,
        List<AttendanceUpdateRequest> requests) {
        return AttendanceUpdateRequests.builder()
            .groupId(groupId)
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
