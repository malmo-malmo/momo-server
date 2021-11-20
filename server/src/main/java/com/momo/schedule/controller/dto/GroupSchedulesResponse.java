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
    private boolean isManager;

    public static GroupSchedulesResponse of(List<GroupScheduleResponse> responses, boolean isManager) {
        return new GroupSchedulesResponse(responses, isManager);
    }
}
