package com.momo.domain.schedule;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.schedule.domain.model.Attendance;
import com.momo.domain.schedule.domain.model.Schedule;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Attendance POJO 테스트")
public class AttendanceTest {

    private Groups group;

    private Schedule schedule;

    @BeforeEach
    void setUp() {
        group = Groups.builder().id(1L).build();
        schedule = Schedule.builder().id(1L).build();
    }

    @Test
    void 출석_리스트_생성_테스트() {
        List<Attendance> actual = List.of(
            Attendance.builder()
                .userId(1L)
                .isAttend(true)
                .build(),
            Attendance.builder()
                .userId(2L)
                .isAttend(false)
                .build()
        );

        List<Attendance> expected = Attendance.createAttendances(actual, group, schedule);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.size()).isEqualTo(actual.size()),
            () -> assertThat(expected.get(0).getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(0).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(0).getUserId()).isEqualTo(actual.get(0).getUserId()),
            () -> assertThat(expected.get(0).isAttend()).isTrue(),
            () -> assertThat(expected.get(1).getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(1).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(1).getUserId()).isEqualTo(actual.get(1).getUserId()),
            () -> assertThat(expected.get(1).isAttend()).isFalse()
        );
    }
}
