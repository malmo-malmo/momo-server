package com.momo.schedule.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupScheduleResponse {

    private Long id;

    private String authorImage;

    private String authorNickname;

    private String title;

    private boolean isOffline;

    private LocalDateTime startDateTime;

    private String contents;

    private boolean isAttendanceCheck;

    private boolean isAttend;

    @QueryProjection
    public GroupScheduleResponse(Long id, String authorImage, String authorNickname, String title, boolean isOffline,
        LocalDateTime startDateTime, String contents, boolean isAttendanceCheck, boolean isAttend) {
        this.id = id;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.isOffline = isOffline;
        this.startDateTime = startDateTime;
        this.contents = contents;
        this.isAttendanceCheck = isAttendanceCheck;
        this.isAttend = isAttend;
    }
}
