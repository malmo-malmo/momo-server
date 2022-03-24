package com.momo.domain.schedule.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class GroupSchedulesRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastScheduleStartDateTime;

    @NotNull(message = "사이즈는 필수값입니다.")
    private Integer size;

    @Builder
    public GroupSchedulesRequest(Long groupId, LocalDateTime lastScheduleStartDateTime, Integer size) {
        this.groupId = groupId;
        this.lastScheduleStartDateTime = lastScheduleStartDateTime;
        this.size = size;
    }
}
