package com.momo.domain.schedule.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Attendance POJO 테스트")
public class AttendanceTest {

    private Group group;

    private Schedule schedule;

    private User user1;

    private User user2;

    @BeforeEach
    void setUp() {
        group = Group.builder().id(1L).build();
        schedule = Schedule.builder().id(1L).build();
        user1 = User.builder().id(1L).build();
        user2 = User.builder().id(2L).build();
    }

    @Test
    void 출석_리스트_생성_테스트() {
        List<Attendance> actual = List.of(
            Attendance.builder()
                .user(user1)
                .isAttend(true)
                .build(),
            Attendance.builder()
                .user(user2)
                .isAttend(false)
                .build()
        );

        List<Attendance> expected = Attendance.createAttendances(actual, group, schedule);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.size()).isEqualTo(actual.size()),
            () -> assertThat(expected.get(0).getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(0).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(0).getUser().getId()).isEqualTo(actual.get(0).getUser().getId()),
            () -> assertThat(expected.get(0).isAttend()).isTrue(),
            () -> assertThat(expected.get(1).getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(1).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(1).getUser().getId()).isEqualTo(actual.get(1).getUser().getId()),
            () -> assertThat(expected.get(1).isAttend()).isFalse()
        );
    }
}
