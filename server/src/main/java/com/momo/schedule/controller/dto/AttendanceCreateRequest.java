package com.momo.schedule.controller.dto;

import com.momo.schedule.domain.model.Attendance;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceCreateRequest {

    @NotNull(message = "유저 ID는 필수값입니다.")
    private Long userId;

    @NotNull(message = "출석 여부는 필수 입력값입니다.")
    private Boolean isAttend;

    public Attendance toEntity() {
        return Attendance.builder()
            .userId(userId)
            .isAttend(isAttend)
            .build();
    }
}
