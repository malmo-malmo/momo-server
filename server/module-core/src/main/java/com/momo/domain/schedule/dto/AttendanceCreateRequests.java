package com.momo.domain.schedule.dto;

import com.momo.domain.schedule.domain.model.Attendance;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttendanceCreateRequests {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @NotNull(message = "일정 ID는 필수값입니다.")
    private Long scheduleId;

    @Valid
    @Size(min = 1)
    @NotNull(message = "출석 리스트는 필수값입니다.")
    private List<AttendanceCreateRequest> attendanceCreateRequests;

    @Builder
    public AttendanceCreateRequests(Long groupId, Long scheduleId,
        List<AttendanceCreateRequest> attendanceCreateRequests) {
        this.groupId = groupId;
        this.scheduleId = scheduleId;
        this.attendanceCreateRequests = attendanceCreateRequests;
    }

    public List<Attendance> toEntities() {
        return attendanceCreateRequests
            .stream()
            .map(AttendanceCreateRequest::toEntity)
            .collect(Collectors.toList());
    }
}
