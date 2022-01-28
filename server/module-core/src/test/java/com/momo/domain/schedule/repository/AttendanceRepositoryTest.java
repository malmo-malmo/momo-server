package com.momo.domain.schedule.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Group;
import com.momo.domain.schedule.entity.Attendance;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("출석 레포지토리 테스트")
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
            .imageUrl("이미지 주소")
            .city(City.SEOUL)
            .district("마포구")
            .university("한국대")
            .build());
        Group group = save(Group.builder()
            .city(City.SEOUL)
            .district("마포")
            .imageUrl("이미지 주소")
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
                .user(manager)
                .isAttend(false)
                .build()
        );
        Attendance attendance = attendanceRepository.findAll().get(0);

        Assertions.assertAll(
            () -> assertThat(attendance.getId()).isNotNull(),
            () -> assertThat(attendance.getGroup()).isEqualTo(group),
            () -> assertThat(attendance.getSchedule()).isEqualTo(schedule),
            () -> assertThat(attendance.getUser()).isEqualTo(manager),
            () -> assertThat(attendance.isAttend()).isFalse()
        );
    }
}
