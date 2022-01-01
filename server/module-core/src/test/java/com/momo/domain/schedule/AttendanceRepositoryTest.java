package com.momo.domain.schedule;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.RepositoryTest;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.schedule.domain.model.Attendance;
import com.momo.domain.schedule.domain.model.Schedule;
import com.momo.domain.schedule.domain.repository.AttendanceRepository;
import com.momo.domain.user.domain.model.SocialProvider;
import com.momo.domain.user.domain.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AttendanceRepositoryTest extends RepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    public void 출석_정보를_저장한다() {
        User manager = save(User.builder()
            .provider(SocialProvider.KAKAO)
            .providerId("test")
            .refreshToken("refresh Token")
            .nickname("testMan")
            .imageUrl("http://~~")
            .city("서울")
            .district("마포구")
            .university("한국대")
            .build());
        Groups group = save(Groups.builder()
            .city("서울")
            .district("마포")
            .imageUrl("http://~")
            .introduction("안녕하세요")
            .university("한국대")
            .isOffline(false)
            .isEnd(false)
            .startDate(LocalDate.now())
            .name("모임 이름")
            .manager(manager)
            .build());
        Schedule schedule = save(Schedule.builder()
            .author(manager)
            .title("오늘의 일정 제목")
            .contents("오늘의 일정")
            .isOffline(false)
            .startDateTime(LocalDateTime.now())
            .build());

        attendanceRepository.save(
            Attendance.builder()
                .group(group)
                .schedule(schedule)
                .userId(manager.getId())
                .isAttend(false)
                .build()
        );
        Attendance attendance = attendanceRepository.findAll().get(0);

        Assertions.assertAll(
            () -> assertThat(attendance.getId()).isNotNull(),
            () -> assertThat(attendance.getGroup()).isEqualTo(group),
            () -> assertThat(attendance.getSchedule()).isEqualTo(schedule),
            () -> assertThat(attendance.getUserId()).isEqualTo(manager.getId()),
            () -> assertThat(attendance.isAttend()).isFalse()
        );
    }
}