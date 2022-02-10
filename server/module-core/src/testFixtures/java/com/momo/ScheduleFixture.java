package com.momo;

import com.momo.domain.group.entity.Group;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;

public class ScheduleFixture {

    private static final String TITLE = "제목";
    private static final String CONTENTS = "내용";
    private static final Boolean IS_OFFLINE = false;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2022, 1, 1, 0, 0);

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 10;
    private static Long INCREASE_ID = 0L;

    public static Schedule getSchedule(User author, Group group) {
        INCREASE_ID++;
        return Schedule.builder()
            .author(author)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .isOffline(IS_OFFLINE)
            .group(group)
            .startDateTime(DATE_TIME)
            .build();
    }

    public static Schedule getScheduleWithId(User author, Group group) {
        INCREASE_ID++;
        return Schedule.builder()
            .id(INCREASE_ID)
            .author(author)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .isOffline(IS_OFFLINE)
            .group(group)
            .startDateTime(DATE_TIME)
            .build();
    }

    public static ScheduleCreateRequest getScheduleCreateRequest(Long groupId, LocalDateTime startDateTime) {
        return ScheduleCreateRequest.builder()
            .groupId(groupId)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .isOffline(IS_OFFLINE)
            .startDateTime(startDateTime)
            .build();
    }

    public static GroupSchedulesRequest getGroupSchedulesRequest(Long groupId) {
        return GroupSchedulesRequest.builder()
            .groupId(groupId)
            .page(PAGE)
            .size(PAGE_SIZE)
            .build();
    }
}
