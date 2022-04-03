package com.momo.schedule.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AttendanceUpdateRequest {

    @NotNull(message = "출석 ID는 필수 입력 값입니다.")
    private Long attendanceId;

    @NotNull(message = "출석 여부는 필수 입력 값입니다.")
    private boolean isAttend;

    @Builder
    public AttendanceUpdateRequest(Long attendanceId, boolean isAttend) {
        this.attendanceId = attendanceId;
        this.isAttend = isAttend;
    }
}
