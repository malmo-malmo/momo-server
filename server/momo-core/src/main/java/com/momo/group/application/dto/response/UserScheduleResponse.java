package com.momo.group.application.dto.response;

import com.momo.common.dto.EnumResponse;
import com.momo.group.domain.schedule.Schedule;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserScheduleResponse {

    private Long groupId;

    private EnumResponse groupCategory;

    private String title;

    private LocalDateTime startDateTime;

    @Builder
    public UserScheduleResponse(Long groupId, EnumResponse groupCategory, String title, LocalDateTime startDateTime) {
        this.groupId = groupId;
        this.groupCategory = groupCategory;
        this.title = title;
        this.startDateTime = startDateTime;
    }

    private static UserScheduleResponse of(Schedule schedule) {
        return UserScheduleResponse.builder()
            .groupId(schedule.getGroup().getId())
            .groupCategory(EnumResponse.ofCategory(schedule.getGroup().getCategory()))
            .title(schedule.getTitle())
            .startDateTime(schedule.getStartDateTime())
            .build();
    }

    public static List<UserScheduleResponse> listOf(List<Schedule> schedules) {
        return schedules.stream().map(UserScheduleResponse::of).collect(Collectors.toList());
    }
}
