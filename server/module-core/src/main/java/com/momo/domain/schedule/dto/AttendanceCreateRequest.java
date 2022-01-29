package com.momo.domain.schedule.dto;

import com.momo.domain.group.entity.Participant;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceCreateRequest {

    @NotNull(message = "참가자 ID는 필수값입니다.")
    private Long participantId;

    @NotNull(message = "출석 여부는 필수 입력값입니다.")
    private boolean isAttend;

    @Builder
    public AttendanceCreateRequest(Long participantId, boolean isAttend) {
        this.participantId = participantId;
        this.isAttend = isAttend;
    }

    public Attendance toEntity(Participant participant, Schedule schedule) {
        return Attendance.builder()
            .participant(participant)
            .schedule(schedule)
            .isAttend(isAttend)
            .build();
    }
}
