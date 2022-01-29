package com.momo.domain.schedule.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
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

    private Participant participant1;

    private Participant participant2;

    @BeforeEach
    void setUp() {
        group = Group.builder().id(1L).build();
        schedule = Schedule.builder().id(1L).build();
        User user = User.builder().id(1L).build();
        participant1 = Participant.builder().id(1L).group(group).user(user).build();
        participant2 = Participant.builder().id(2L).group(group).user(user).build();
    }

    @Test
    void 출석_리스트_생성_테스트() {
        List<Attendance> actual = List.of(
            Attendance.builder()
                .participant(participant1)
                .isAttend(true)
                .build(),
            Attendance.builder()
                .participant(participant2)
                .isAttend(false)
                .build()
        );

        List<Attendance> expected = Attendance.createAttendances(actual, schedule);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.size()).isEqualTo(actual.size()),
            () -> assertThat(expected.get(0).getParticipant().getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(0).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(0).getParticipant().getUser().getId()).isEqualTo(
                actual.get(0).getParticipant().getUser().getId()),
            () -> assertThat(expected.get(0).isAttend()).isTrue(),
            () -> assertThat(expected.get(1).getParticipant().getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(1).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(1).getParticipant().getUser().getId()).isEqualTo(
                actual.get(1).getParticipant().getUser().getId()),
            () -> assertThat(expected.get(1).isAttend()).isFalse()
        );
    }
}
