package com.momo.group.application.dto.response;

import com.momo.group.domain.schedule.Schedule;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpcomingScheduleResponse {

    private String title;
    private LocalDateTime startDateTime;

    @Builder
    public UpcomingScheduleResponse(String title, LocalDateTime startDateTime) {
        this.title = title;
        this.startDateTime = startDateTime;
    }


    public static UpcomingScheduleResponse of(Schedule schedule) {
        if (Objects.isNull(schedule)) {
            return UpcomingScheduleResponse.builder().build();
        }

        return UpcomingScheduleResponse.builder()
            .title(schedule.getTitle())
            .startDateTime(schedule.getStartDateTime())
            .build();
    }
}
