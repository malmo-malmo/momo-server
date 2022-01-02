package com.momo.domain.group.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.entity.Groups;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Participant POJO 테스트")
public class ParticipantTest {

    private User user;

    private Groups group;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).build();
        group = Groups.builder()
            .id(1L)
            .manager(user)
            .build();
    }

    @Test
    void 참여자_생성_테스트() {
        Participant expected = Participant.create(user, group);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getUser().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getGroup().getId()).isEqualTo(group.getId())
        );
    }

    @Test
    void 참여자_출석률_계산_테스트() {
        Participant expected = Participant.builder()
            .scheduleCount(10)
            .attendanceCount(5)
            .build();
        expected.calculateAttendanceRate();
        assertThat(expected.getAttendanceRate()).isEqualTo(50);
    }

    @Test
    void 출석_수가_0일_경우_참여자_출석률_계산_테스트() {
        Participant expected = Participant.builder()
            .scheduleCount(10)
            .attendanceCount(0)
            .build();
        expected.calculateAttendanceRate();
        assertThat(expected.getAttendanceRate()).isEqualTo(0);
    }

    @Test
    void 일정_수가_0일_경우_참여자_출석률_계산_테스트() {
        Participant expected = Participant.builder()
            .scheduleCount(0)
            .attendanceCount(5)
            .build();
        expected.calculateAttendanceRate();
        assertThat(expected.getAttendanceRate()).isEqualTo(0);
    }
}
