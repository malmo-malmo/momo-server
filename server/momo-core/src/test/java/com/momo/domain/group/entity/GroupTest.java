package com.momo.domain.group.entity;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Groups POJO 테스트")
public class GroupTest {

    @Test
    void 모임_생성_테스트() {
        User user = getUserWithId();
        Group group = getGroupWithId(user);

        Group expected = Group.create(user, group, true);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getLocation().getUniversity()).isEqualTo(user.getLocation().getUniversity())
        );
    }

    @Test
    void 모임_담당자_여부_확인_테스트() {
        User user = getUserWithId();
        Group group = getGroupWithId(user);

        boolean expected = group.isManager(user);

        assertThat(expected).isTrue();
    }

    @Test
    void 모임_담당자_변경_테스트() {
        User user1 = getUserWithId();
        User user2 = getUserWithId();
        Group group = getGroupWithId(user1);

        group.updateManager(user2);

        assertThat(group.getManager().getId()).isEqualTo(user2.getId());
    }

    @Test
    void 모임_종료_테스트() {
        Group expected = getGroupWithId(getUserWithId());

        expected.endGroup();

        assertThat(expected.isEnd()).isTrue();
    }
}
