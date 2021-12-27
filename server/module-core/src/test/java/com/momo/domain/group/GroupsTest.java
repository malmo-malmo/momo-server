package com.momo.domain.group;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Groups POJO 테스트")
public class GroupsTest {

    @Test
    void 모임_생성_테스트() {
        User user = User.builder()
            .id(1L)
            .university("대학교")
            .build();
        Groups group = Groups.builder().id(1L).build();
        Groups expected = Groups.create(user, group, true);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getUniversity()).isEqualTo(user.getUniversity())
        );
    }

    @Test
    void 모임_담당자_여부_확인_테스트() {
        User user = User.builder().id(1L).build();
        Groups group = Groups.builder()
            .id(1L)
            .manager(user)
            .build();

        boolean expected = group.isManager(user);
        assertThat(expected).isTrue();
    }

    @Test
    void 모임_담당자_변경_테스트() {
        Groups expected = Groups.builder()
            .id(1L)
            .manager(User.builder().id(1L).build())
            .build();

        User actual = User.builder().id(2L).build();
        expected.updateManager(actual);

        assertThat(expected.getManager().getId().equals(actual.getId()));
    }

    @Test
    void 모임_종료_테스트() {
        Groups expected = Groups.builder().id(1L).build();
        expected.endGroup();
        assertThat(expected.isEnd()).isTrue();
    }
}
