package com.momo.domain.schedule.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttendanceUpdateRequest {
    @NotNull(message = "출석 ID는 필수 입력 값입니다.")
    private Long attendanceId;
    @NotNull(message = "출석 여부는 필수 입력 값입니다.")
    private Boolean isAttend;

    @Builder
    public AttendanceUpdateRequest(Long attendanceId, Boolean isAttend) {
        this.attendanceId = attendanceId;
        this.isAttend = isAttend;
    }
}