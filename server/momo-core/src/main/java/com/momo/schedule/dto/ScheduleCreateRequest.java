package com.momo.schedule.dto;

import com.momo.schedule.entity.Schedule;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class ScheduleCreateRequest {

    @NotNull(message = "모임 ID는 필수 입력값입니다.")
    private Long groupId;

    @NotBlank(message = "일정명은 필수 입력값입니다.")
    private String title;

    @NotNull(message = "온 오프라인 여부는 필수 입력값입니다.")
    private Boolean isOffline;

    @NotNull(message = "일정 시작시간은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;

    @NotBlank(message = "일정 내용은 필수 입력값입니다.")
    private String contents;

    @Builder
    public ScheduleCreateRequest(Long groupId, String title, Boolean isOffline, LocalDateTime startDateTime,
        String contents) {
        this.groupId = groupId;
        this.title = title;
        this.isOffline = isOffline;
        this.startDateTime = startDateTime;
        this.contents = contents;
    }

    public Schedule toEntity() {
        return Schedule.builder()
            .title(title)
            .isOffline(isOffline)
            .startDateTime(startDateTime)
            .contents(contents)
            .build();
    }
}
