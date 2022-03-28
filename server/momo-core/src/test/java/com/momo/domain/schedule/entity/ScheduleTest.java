package com.momo.domain.schedule.entity;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.ScheduleFixture.getSchedule;
import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.entity.Group;
import com.momo.domain.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Schedule POJO 테스트")
public class ScheduleTest {

    private User user;
    private Group group;

    @BeforeEach
    void setUp() {
        user = getUserWithId();
        group = getGroupWithId(user);
    }

    @Test
    void 일정_생성_테스트() {
        Schedule actual = getSchedule(user, group);
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
        Schedule schedule = getSchedule(user, group);
        Schedule expected = Schedule.create(schedule, group, user);

        expected.updateAttendanceCheck(true);

        assertThat(expected.isAttendanceCheck()).isTrue();
    }
}
