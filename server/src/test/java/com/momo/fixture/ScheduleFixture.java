package com.momo.fixture;

import com.momo.schedule.controller.dto.ScheduleCreateRequest;
import java.time.LocalDateTime;

public class ScheduleFixture {

    public static ScheduleCreateRequest SCHEDULE_CREATE_REQUEST1 = ScheduleCreateRequest.builder()
        .title("11월 둘째 주 정기모임")
        .isOffline(true)
        .startDateTime(LocalDateTime.of(2021, 11, 14, 12, 30))
        .contents("11월 둘째 주 정기모임 많은 참석 부탁드립니다.")
        .build();

    public static ScheduleCreateRequest SCHEDULE_CREATE_REQUEST2 = ScheduleCreateRequest.builder()
        .title("11월 셋째 주 정기모임")
        .isOffline(true)
        .startDateTime(LocalDateTime.of(2021, 11, 25, 12, 30))
        .contents("11월 셋째 주 정기모임 많은 참석 부탁드립니다.")
        .build();
}
