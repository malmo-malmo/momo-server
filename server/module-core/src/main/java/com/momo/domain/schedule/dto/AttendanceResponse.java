package com.momo.domain.schedule.dto;

import com.momo.domain.schedule.entity.Attendance;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttendanceResponse {

    private Long attendanceId;
    private String username;
    private boolean isAttend;
    private int attainmentRate;

    @Builder
    public AttendanceResponse(Long attendanceId, String username, boolean isAttend, int attainmentRate) {
        this.attendanceId = attendanceId;
        this.username = username;
        this.isAttend = isAttend;
        this.attainmentRate = attainmentRate;
    }

    public AttendanceResponse(Attendance attendance) {
        this(
            attendance.getId(),
            attendance.getUser().getNickname(),
            attendance.isAttend(),
            100
        );
    }
}