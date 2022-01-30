package com.momo.domain.schedule.dto;

import com.momo.domain.schedule.entity.Attendance;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttendanceResponse {

    private Long attendanceId;
    private String username;
    private Boolean isAttend;
    private int achievementRate;

    @Builder
    public AttendanceResponse(Long attendanceId, String username, Boolean isAttend, int achievementRate) {
        this.attendanceId = attendanceId;
        this.username = username;
        this.isAttend = isAttend;
        this.achievementRate = achievementRate;
    }

    public static AttendanceResponse of(Attendance attendance) {
        return AttendanceResponse.builder()
            .attendanceId(attendance.getId())
            .username(attendance.getParticipant().getUser().getNickname())
            .isAttend(attendance.isAttend())
            .achievementRate(100)
            .build();
    }

    public static List<AttendanceResponse> listOf(List<Attendance> attendances) {
        return attendances.stream().map(AttendanceResponse::of).collect(Collectors.toList());
    }
}