package com.momo.domain.group.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.user.entity.Location;
import com.momo.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Groups POJO 테스트")
public class GroupTest {

    @Test
    void 모임_생성_테스트() {
        User user = User.builder()
            .id(1L)
            .location(Location.builder().university("대학교").build())
            .build();
        Group group = Group.builder().id(1L).build();
        Group expected = Group.create(user, group, true);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getLocation().getUniversity()).isEqualTo(user.getLocation().getUniversity())
        );
    }

    @Test
    void 모임_담당자_여부_확인_테스트() {
        User user = User.builder().id(1L).build();
        Group group = Group.builder()
            .id(1L)
            .manager(user)
            .build();

        boolean expected = group.isManager(user);
        assertThat(expected).isTrue();
    }

    @Test
    void 모임_담당자_변경_테스트() {
        Group expected = Group.builder()
            .id(1L)
            .manager(User.builder().id(1L).build())
            .build();

        User actual = User.builder().id(2L).build();
        expected.updateManager(actual);

        assertThat(expected.getManager().getId().equals(actual.getId()));
    }

    @Test
    void 모임_종료_테스트() {
        Group expected = Group.builder().id(1L).build();
        expected.endGroup();
        assertThat(expected.isEnd()).isTrue();
    }
}
