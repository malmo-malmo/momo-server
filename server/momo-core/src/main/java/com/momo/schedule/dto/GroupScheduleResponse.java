package com.momo.schedule.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupScheduleResponse {

    private Long scheduleId;
    private String authorImage;
    private String authorNickname;
    private String title;
    private LocalDateTime startDateTime;
    private String contents;
    private boolean isOffline;
    private boolean attendanceCheck;
    private boolean isAttend;

    @Builder
    @QueryProjection
    public GroupScheduleResponse(Long scheduleId, String authorImage, String authorNickname, String title,
        boolean isOffline, LocalDateTime startDateTime, String contents, boolean attendanceCheck, boolean isAttend) {
        this.scheduleId = scheduleId;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.isOffline = isOffline;
        this.startDateTime = startDateTime;
        this.contents = contents;
        this.attendanceCheck = attendanceCheck;
        this.isAttend = isAttend;
    }
}
