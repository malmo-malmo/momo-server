package com.momo.domain.schedule.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.entity.Groups;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Schedule POJO 테스트")
public class ScheduleTest {

    private User user;

    private Groups group;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).build();
        group = Groups.builder().id(1L).build();
    }

    @Test
    void 일정_생성_테스트() {
        Schedule actual = Schedule.builder()
            .isOffline(true)
            .startDateTime(LocalDateTime.now())
            .contents("내용")
            .build();
        Schedule expected = Schedule.create(actual, group, user);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getAuthor().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.isOffline()).isEqualTo(actual.isOffline()),
            () -> assertThat(expected.getStartDateTime()).isEqualTo(actual.getStartDateTime()),
            () -> assertThat(expected.getContents()).isEqualTo(actual.getContents()),
            () -> assertThat(expected.isAttendanceCheck()).isFalse()
        );
    }

    @Test
    void 일정_출석_체크_여부_테스트() {
        Schedule schedule = Schedule.builder()
            .isOffline(true)
            .startDateTime(LocalDateTime.now())
            .contents("내용")
            .build();
        Schedule expected = Schedule.create(schedule, group, user);
        expected.updateAttendanceCheck(true);
        assertThat(expected.isAttendanceCheck()).isTrue();
    }
}
