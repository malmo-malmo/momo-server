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

    private Schedule schedule;

    @BeforeEach
    void setup() {
        manager = save(User.builder()
            .provider(SocialProvider.KAKAO)
            .providerId("test")
            .refreshToken("refresh Token")
            .nickname("testMan")
            .imageUrl("이미지 주소")
            .city(City.SEOUL)
            .district("마포구")
            .university("한국대")
            .build());
        group = save(Group.builder()
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
        schedule = save(Schedule.builder()
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
    }

    @Test
    void 출석_정보를_저장한다() {
        Attendance attendance = attendanceRepository.findAll().get(0);

        Assertions.assertAll(
            () -> assertThat(attendance.getId()).isNotNull(),
            () -> assertThat(attendance.getGroup()).isEqualTo(group),
            () -> assertThat(attendance.getSchedule()).isEqualTo(schedule),
            () -> assertThat(attendance.getUser()).isEqualTo(manager),
            () -> assertThat(attendance.isAttend()).isFalse()
        );
    }
    @Test
    void 해당_유저의_출석_정보를_가져온다() {
        Attendance attendance = attendanceRepository.findByUserId(manager.getId()).get();

        Assertions.assertAll(
            () -> assertThat(attendance.getId()).isNotNull(),
            () -> assertThat(attendance.getGroup()).isEqualTo(group),
            () -> assertThat(attendance.getSchedule()).isEqualTo(schedule),
            () -> assertThat(attendance.getUser()).isEqualTo(manager),
            () -> assertThat(attendance.isAttend()).isFalse()
        );
    }
    @Test
    void 해당_그룹의_출석_목록을_가져온다() {
        List<Attendance> attendances = attendanceRepository.findByGroup(group);
        assertThat(attendances.size()).isEqualTo(1);

        Attendance attendance = attendances.get(0);
        Assertions.assertAll(
            () -> assertThat(attendance.getId()).isNotNull(),
            () -> assertThat(attendance.getGroup()).isEqualTo(group),
            () -> assertThat(attendance.getSchedule()).isEqualTo(schedule),
            () -> assertThat(attendance.getUser()).isEqualTo(manager),
            () -> assertThat(attendance.isAttend()).isFalse()
        );
    }
}
