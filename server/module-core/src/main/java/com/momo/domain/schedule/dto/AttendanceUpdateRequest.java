package com.momo.domain.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttendanceUpdateRequest {
    private Long attendanceId;
    private boolean isAttend;

    @Builder
    public AttendanceUpdateRequest(Long attendanceId, boolean isAttend) {
        this.attendanceId = attendanceId;
        this.isAttend = isAttend;
    }
}