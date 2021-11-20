package com.momo.fixture;

import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import java.time.LocalDateTime;

public class ScheduleFixture {

    public static ScheduleCreateRequest SCHEDULE_CREATE_REQUEST1 = ScheduleCreateRequest.builder()
        .title("11월 첫째 주 정기모임")
        .isOffline(true)
        .startDateTime(LocalDateTime.of(2021, 11, 1, 0, 0))
        .contents("11월 첫째 주 정기모임 많은 참석 부탁드립니다.")
        .build();

    public static ScheduleCreateRequest SCHEDULE_CREATE_REQUEST2 = ScheduleCreateRequest.builder()
        .title("11월 셋째 주 정기모임")
        .isOffline(true)
        .startDateTime(LocalDateTime.of(2021, 11, 30, 11, 59))
        .contents("11월 넷째 주 정기모임 많은 참석 부탁드립니다.")
        .build();
}
