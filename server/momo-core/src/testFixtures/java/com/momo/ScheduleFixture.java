package com.momo;

import static com.momo.common.FixtureComponents.CONTENTS;
import static com.momo.common.FixtureComponents.DATE_TIME;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.IS_OFFLINE;
import static com.momo.common.FixtureComponents.PAGE;
import static com.momo.common.FixtureComponents.PAGE_SIZE;
import static com.momo.common.FixtureComponents.TITLE;

import com.momo.domain.group.entity.Group;
import com.momo.domain.schedule.dto.GroupSchedulesRequest;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.dto.UpcomingScheduleResponse;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;

public class ScheduleFixture {

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

    public static Schedule getCustomDateSchedule(User author, Group group, LocalDateTime startDate) {
        INCREASE_ID++;
        return Schedule.builder()
            .author(author)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .isOffline(IS_OFFLINE)
            .group(group)
            .startDateTime(startDate)
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
        INCREASE_ID++;
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

    public static UpcomingScheduleResponse getUpcomingScheduleResponse() {
        return UpcomingScheduleResponse.builder()
            .title(TITLE)
            .startDateTime(DATE_TIME)
            .build();
    }
}
