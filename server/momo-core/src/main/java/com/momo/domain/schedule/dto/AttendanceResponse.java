package com.momo.domain.schedule.dto;

import com.momo.domain.schedule.entity.Attendance;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AttendanceResponse {

    private Long attendanceId;
    private String nickname;
    private String imageUrl;
    private Boolean isAttend;
    private int attendanceRate;

    @Builder
    public AttendanceResponse(Long attendanceId, String nickname, String imageUrl, Boolean isAttend,
        int attendanceRate) {
        this.attendanceId = attendanceId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.isAttend = isAttend;
        this.attendanceRate = attendanceRate;
    }

    public static AttendanceResponse of(Attendance attendance) {
        return AttendanceResponse.builder()
            .attendanceId(attendance.getId())
            .nickname(attendance.getParticipant().getUser().getNickname())
            .imageUrl(attendance.getParticipant().getUser().getImageUrl())
            .isAttend(attendance.isAttend())
            .attendanceRate(100)
            .build();
    }

    public static List<AttendanceResponse> listOf(List<Attendance> attendances) {
        return attendances.stream()
            .map(AttendanceResponse::of)
            .collect(Collectors.toList());
    }
}
