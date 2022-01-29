package com.momo.domain.schedule.dto;

import javax.validation.constraints.NotNull;

import com.momo.domain.schedule.entity.Attendance;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceCreateRequest {

    /*
    TODO
    userId ->  participantId
    */

    @NotNull(message = "유저 ID는 필수값입니다.")
    private Long userId;

    @NotNull(message = "출석 여부는 필수 입력값입니다.")
    private Boolean isAttend;

    @Builder
    public AttendanceCreateRequest(Long userId, Boolean isAttend) {
        this.userId = userId;
        this.isAttend = isAttend;
    }

    public Attendance toEntity() {
        return Attendance.builder()
            .userId(userId)
            .isAttend(isAttend)
            .build();
    }
}
