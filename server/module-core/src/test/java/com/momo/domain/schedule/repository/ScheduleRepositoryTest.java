package com.momo.domain.schedule.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@DisplayName("일정 도메인 Repository 테스트")
public class ScheduleRepositoryTest extends RepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    private User user;

    private Group group;

    private Schedule schedule;

    @BeforeEach
    public void before() {
        user = save(User.builder()
            .provider(SocialProvider.KAKAO)
            .providerId("test")
            .refreshToken("refresh Token")
            .nickname("testMan")
            .imageUrl("http://~~")
            .city(City.SEOUL)
            .district("마포구")
            .university("한국대")
            .build());
        group = save(Group.builder()
            .city(City.SEOUL)
            .district("마포")
            .imageUrl("http://~")
            .introduction("안녕하세요")
            .university("한국대")
            .isOffline(false)
            .isEnd(false)
            .startDate(LocalDate.now())
            .name("모임 이름")
            .manager(user)
            .build());
        schedule = scheduleRepository.save(Schedule.builder()
            .author(user)
            .title("오늘의 일정 제목")
            .contents("오늘의 일정")
            .isOffline(false)
            .group(group)
            .startDateTime(LocalDateTime.of(2022, 01, 01, 11, 05, 11))
            .build());
    }
    @Test
    public void 일정을_저장한다() {
        assertThat(schedule).isNotNull();
        assertThat(schedule.getId()).isNotNull();
    }
    @Test
    public void 모임과_유저를_조건으로_모임_일정을_조회한다() {
        save(Attendance.builder()
                .group(group)
                .schedule(schedule)
                .userId(user.getId())
                .isAttend(false)
                .build()
        );

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<GroupScheduleResponse> scheduleResponseList = scheduleRepository.findAllByGroupAndUserOrderByCreatedDateDesc(group, user.getId(), pageRequest);
        assertThat(scheduleResponseList.size()).isEqualTo(1);
        GroupScheduleResponse scheduleResponse = scheduleResponseList.get(0);
        Assertions.assertAll(
            () -> assertThat(scheduleResponse.getId()).isEqualTo(schedule.getId()),
            () -> assertThat(scheduleResponse.getAuthorImage()).isEqualTo("http://~~"),
            () -> assertThat(scheduleResponse.getAuthorNickname()).isEqualTo("testMan"),
            () -> assertThat(scheduleResponse.getTitle()).isEqualTo("오늘의 일정 제목"),
            () -> assertThat(scheduleResponse.isOffline()).isFalse(),
            () -> assertThat(scheduleResponse.getStartDateTime()).isNotNull(),
            () -> assertThat(scheduleResponse.getContents()).isEqualTo("오늘의 일정"),
            () -> assertThat(scheduleResponse.isAttendanceCheck()).isFalse(),
            () -> assertThat(scheduleResponse.isAttend()).isFalse()
        );
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void 시작일자와_종료일자_사이에_해당하는_모임_일정을_조회한다() {
        LocalDateTime startTime = LocalDateTime.of(2022, 01, 01, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2022, 01, 02, 23, 59);

        save(Participant.builder()
            .group(group)
            .user(user)
            .build());
        Schedule findSchedule = scheduleRepository.findAllByStartDateTimeBetween(startTime, endTime, user).get(0);
        assertThat(schedule).isEqualTo(findSchedule);
    }
}
