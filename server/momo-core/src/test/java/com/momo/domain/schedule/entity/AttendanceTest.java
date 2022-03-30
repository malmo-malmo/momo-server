package com.momo.domain.schedule.entity;

import static com.momo.AttendanceFixture.getAttendance;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.ParticipantFixture.getParticipantWithId;
import static com.momo.ScheduleFixture.getScheduleWithId;
import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.schedule.entity.Attendance;
import com.momo.schedule.entity.Schedule;
import com.momo.user.domain.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Attendance POJO 테스트")
public class AttendanceTest {

    private Group group;
    private Schedule schedule;
    private Participant participant;

    @BeforeEach
    void setUp() {
        User user = getUserWithId();
        group = getGroupWithId(user);
        schedule = getScheduleWithId(user, group);
        participant = getParticipantWithId(group, user);
    }

    @Test
    void 출석_리스트_생성_테스트() {
        List<Attendance> actual = List.of(getAttendance(schedule, participant, true));

        List<Attendance> expected = Attendance.createAttendances(actual, schedule);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.size()).isEqualTo(actual.size()),
            () -> assertThat(expected.get(0).getParticipant().getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(0).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(0).isAttend()).isTrue(),
            () -> assertThat(expected.get(0).getParticipant().getUser().getId())
                .isEqualTo(actual.get(0).getParticipant().getUser().getId())
        );
    }
}
