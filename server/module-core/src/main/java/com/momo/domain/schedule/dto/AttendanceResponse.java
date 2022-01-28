package com.momo.domain.schedule.dto;

import com.momo.domain.schedule.entity.Attendance;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttendanceResponse {

    private Long userId;
    private String username;
    private boolean isAttend;

    @Builder
    public AttendanceResponse(Long userId, String username, boolean isAttend) {
        this.userId = userId;
        this.username = username;
        this.isAttend = isAttend;
    }

    public AttendanceResponse(Attendance attendance) {
        this(
            attendance.getUser().getId(),
            attendance.getUser().getNickname(),
            attendance.isAttend()
        );
    }
}