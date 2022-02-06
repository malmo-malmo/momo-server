package com.momo.domain.schedule.repository;

import static com.momo.AttendanceFixture.getAttendance;
import static com.momo.GroupFixture.getGroup;
import static com.momo.ParticipantFixture.getParticipant;
import static com.momo.ScheduleFixture.getSchedule;
import static com.momo.UserFixture.getUser;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("출석 레포지토리 테스트")
public class AttendanceRepositoryTest extends RepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    private User manager;
    private Group group;
    private Participant participant;
    private Schedule schedule;
    private Attendance attendance;

    @BeforeEach
    void setup() {
        manager = save(getUser());
        group = save(getGroup(manager));
        schedule = save(getSchedule(manager, group));
        participant = save(getParticipant(group, manager));
        attendance = save(getAttendance(schedule, participant, false));
    }

    @Test
    void 출석_정보를_저장한다() {
        Attendance attendance = attendanceRepository.findAll().get(0);

        Assertions.assertAll(
            () -> assertThat(attendance.getId()).isNotNull(),
            () -> assertThat(attendance.getParticipant().getGroup()).isEqualTo(group),
            () -> assertThat(attendance.getSchedule()).isEqualTo(schedule),
            () -> assertThat(attendance.getParticipant().getUser()).isEqualTo(manager),
            () -> assertThat(attendance.isAttend()).isFalse()
        );
    }

    @Test
    void 해당_일정의_출석_목록을_가져온다() {
        List<Attendance> expected = attendanceRepository.findBySchedule(schedule);

        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(1),
            () -> assertThat(expected.get(0).getId()).isEqualTo(attendance.getId()),
            () -> assertThat(expected.get(0).getParticipant().getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.get(0).getSchedule().getId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(0).getParticipant().getUser().getId()).isEqualTo(manager.getId()),
            () -> assertThat(expected.get(0).isAttend()).isFalse()
        );
    }

    @Test
    void 출석_ID로_출석_목록을_조회한다() {
        Long id1 = save(getAttendance(schedule, participant, false)).getId();
        Long id2 = save(getAttendance(schedule, participant, false)).getId();
        Long id3 = save(getAttendance(schedule, participant, false)).getId();
        List<Long> list = List.of(id3, id2, id1);

        List<Attendance> attendances = attendanceRepository.findAllByIds(list);

        Assertions.assertAll(
            () -> assertThat(attendances.size()).isEqualTo(3),
            () -> assertThat(attendances.get(0).getId()).isEqualTo(id3),
            () -> assertThat(attendances.get(1).getId()).isEqualTo(id2),
            () -> assertThat(attendances.get(2).getId()).isEqualTo(id1)
        );
    }
}
