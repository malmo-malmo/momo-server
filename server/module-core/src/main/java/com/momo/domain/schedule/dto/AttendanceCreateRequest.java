package com.momo.domain.schedule.dto;

import com.momo.domain.group.entity.Group;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.User;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceCreateRequest {

    @NotNull(message = "유저 ID는 필수값입니다.")
    private Long userId;

    @NotNull(message = "출석 여부는 필수 입력값입니다.")
    private Boolean isAttend;

    @Builder
    public AttendanceCreateRequest(Long userId, Boolean isAttend) {
        this.userId = userId;
        this.isAttend = isAttend;
    }

    public Attendance toEntity(User user, Group group, Schedule schedule) {
        return Attendance.builder()
            .group(group)
            .schedule(schedule)
            .user(user)
            .isAttend(isAttend)
            .build();
    }
}
