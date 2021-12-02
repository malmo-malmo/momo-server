package com.momo.schedule.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupSchedulesResponse {

    private List<GroupScheduleResponse> schedules;
    private Long managerId;

    public static GroupSchedulesResponse of(List<GroupScheduleResponse> responses, Long managerId) {
        return new GroupSchedulesResponse(responses, managerId);
    }
}
