package com.momo.schedule.controller.dto;

import com.momo.schedule.domain.model.Attendance;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceCreateRequests {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @NotNull(message = "일정 ID는 필수값입니다.")
    private Long scheduleId;

    private List<AttendanceCreateRequest> attendanceCreateRequests;

    public List<Attendance> toEntities() {
        return attendanceCreateRequests
            .stream()
            .map(AttendanceCreateRequest::toEntity)
            .collect(Collectors.toList());
    }
}
