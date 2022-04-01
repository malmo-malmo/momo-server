package com.momo.unit.schedule.repository;

import static com.momo.GroupFixture.getGroup;
import static com.momo.ParticipantFixture.getParticipant;
import static com.momo.ScheduleFixture.getCustomDateSchedule;
import static com.momo.ScheduleFixture.getSchedule;
import static com.momo.UserFixture.getUser;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.group.entity.Group;
import com.momo.schedule.dto.GroupScheduleResponse;
import com.momo.schedule.entity.Schedule;
import com.momo.schedule.repository.ScheduleRepository;
import com.momo.user.domain.model.User;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("일정 레포지토리 테스트")
public class ScheduleRepositoryTest extends RepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    private User user;
    private Group group;
    private Schedule schedule;

    @BeforeEach
    void before() {
        user = save(getUser());
        group = save(getGroup(user));
        schedule = save(getSchedule(user, group));
        save(getParticipant(group, user));
    }

    @Test
    void 일정을_저장한다() {
        Assertions.assertAll(
            () -> assertThat(schedule).isNotNull(),
            () -> assertThat(schedule.getId()).isNotNull()
        );
    }

    @Test
    void 모임과_유저를_조건으로_모임_일정을_조회한다() {
        List<GroupScheduleResponse> expected = scheduleRepository.findAllByGroupOrderByStartDateTimeDesc(
            group, user.getId(), schedule.getStartDateTime().plusMinutes(1), 10
        );
        verifyGroupScheduleResponse(expected);
    }

    @Test
    void 모임과_유저를_조건으로_모임_일정을_조회한다_마지막_일정_시작시간_NULL() {
        List<GroupScheduleResponse> expected = scheduleRepository.findAllByGroupOrderByStartDateTimeDesc(
            group, user.getId(), null, 10
        );
        verifyGroupScheduleResponse(expected);
    }

    private void verifyGroupScheduleResponse(List<GroupScheduleResponse> expected) {
        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(1),
            () -> assertThat(expected.get(0).getScheduleId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.get(0).getAuthorImage()).isEqualTo(user.getImageUrl()),
            () -> assertThat(expected.get(0).getAuthorNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(expected.get(0).getTitle()).isEqualTo(schedule.getTitle()),
            () -> assertThat(expected.get(0).isOffline()).isFalse(),
            () -> assertThat(expected.get(0).getStartDateTime()).isNotNull(),
            () -> assertThat(expected.get(0).getContents()).isEqualTo(schedule.getContents()),
            () -> assertThat(expected.get(0).isAttendanceCheck()).isFalse(),
            () -> assertThat(expected.get(0).isAttend()).isFalse()
        );
    }

    @Test
    void 시작일자와_종료일자_사이에_해당하는_모임_일정을_조회한다() {
        LocalDateTime startTime = of(2022, 1, 1, 0, 0);
        LocalDateTime endTime = of(2022, 1, 2, 23, 59);

        Schedule expected = scheduleRepository.findAllByStartDateTimeBetween(startTime, endTime, user).get(0);

        assertThat(expected.getId()).isEqualTo(schedule.getId());
    }

    @Test
    void 일정_ID로_일정_정보를_조회한다() {
        GroupScheduleResponse expected = scheduleRepository.findGroupResponseById(schedule.getId(), user.getId());

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getScheduleId()).isEqualTo(schedule.getId()),
            () -> assertThat(expected.getAuthorImage()).isEqualTo(schedule.getAuthor().getImageUrl()),
            () -> assertThat(expected.getTitle()).isEqualTo(schedule.getTitle()),
            () -> assertThat(expected.isOffline()).isEqualTo(schedule.isOffline()),
            () -> assertThat(expected.getStartDateTime()).isEqualTo(schedule.getStartDateTime()),
            () -> assertThat(expected.getContents()).isEqualTo(schedule.getContents())
        );
    }

    @Test
    void 다가오는_일정을_조회한다() {
        Schedule actual = save(getCustomDateSchedule(user, group, of(2022, 2, 21, 10, 30)));
        save(getCustomDateSchedule(user, group, of(2022, 2, 21, 10, 32)));

        Schedule expected = scheduleRepository
            .findFirstByGroupAndStartDateTimeAfter(group, of(2022, 2, 21, 10, 29));

        assertThat(expected.getId()).isEqualTo(actual.getId());
    }
}
