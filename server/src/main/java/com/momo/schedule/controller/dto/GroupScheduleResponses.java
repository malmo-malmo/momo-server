package com.momo.schedule.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupScheduleResponses {

    private List<GroupScheduleResponse> groupScheduleResponses;
    private Long managerId;

    public static GroupScheduleResponses of(List<GroupScheduleResponse> responses, Long managerId) {
        return new GroupScheduleResponses(responses, managerId);
    }
}
