package com.momo.fixture;

import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import java.time.LocalDateTime;

public class ScheduleFixture {

    public static ScheduleCreateRequest getScheduleCreateRequest1(Long groupId) {
        return ScheduleCreateRequest.builder()
            .groupId(groupId)
            .title("11월 첫째 주 정기모임")
            .isOffline(true)
            .startDateTime(LocalDateTime.of(2021, 11, 1, 0, 0))
            .contents("11월 첫째 주 정기모임 많은 참석 부탁드립니다.")
            .build();
    }

    public static ScheduleCreateRequest getScheduleCreateRequest2(Long groupId) {
        return ScheduleCreateRequest.builder()
            .groupId(groupId)
            .title("11월 셋째 주 정기모임")
            .isOffline(true)
            .startDateTime(LocalDateTime.of(2021, 11, 30, 11, 59))
            .contents("11월 넷째 주 정기모임 많은 참석 부탁드립니다.")
            .build();
    }
}
