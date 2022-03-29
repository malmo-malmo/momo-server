package com.momo.schedule.dto;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class AttendanceUpdateRequests {

    @NotNull(message = "일정 ID는 필수값입니다.")
    private Long scheduleId;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<AttendanceUpdateRequest> attendanceUpdateRequests;

    @Builder
    public AttendanceUpdateRequests(Long scheduleId, List<AttendanceUpdateRequest> attendanceUpdateRequests) {
        this.scheduleId = scheduleId;
        this.attendanceUpdateRequests = attendanceUpdateRequests;
    }

    public List<Long> toAttendanceIds() {
        return attendanceUpdateRequests.stream()
            .map(AttendanceUpdateRequest::getAttendanceId)
            .collect(Collectors.toList());
    }
}
