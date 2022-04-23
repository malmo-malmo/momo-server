package com.momo.unit.group.domain;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.domain.Group;
import com.momo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Groups POJO 테스트")
public class GroupTest {

    @Test
    @DisplayName("모임을 생성한다")
    void createGroup_Success() {
        User user = getUserWithId();
        Group group = getGroupWithId(user);

        Group expected = Group.create(user, group);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getName()).isEqualTo(group.getName()),
            () -> assertThat(expected.getImageUrl()).isEqualTo(group.getImageUrl()),
            () -> assertThat(expected.getIntroduction()).isEqualTo(group.getIntroduction()),
            () -> assertThat(expected.getRecruitmentCnt()).isEqualTo(group.getRecruitmentCnt()),
            () -> assertThat(expected.getStartDate()).isEqualTo(group.getStartDate()),
            () -> assertThat(expected.isOffline()).isEqualTo(group.isOffline()),
            () -> assertThat(expected.getUniversity()).isEqualTo(group.getUniversity()),
            () -> assertThat(expected.getLocation().getCity()).isEqualTo(group.getLocation().getCity()),
            () -> assertThat(expected.getLocation().getDistrict()).isEqualTo(group.getLocation().getDistrict())
        );
    }

    @Test
    @DisplayName("모임 정보를 수정한다")
    void updateGroup_Success() {
        Group expected = getGroupWithId(getUserWithId());
        Group actual = getGroupWithId(getUserWithId());

        expected.update(actual);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getName()).isEqualTo(actual.getName()),
            () -> assertThat(expected.getImageUrl()).isEqualTo(actual.getImageUrl()),
            () -> assertThat(expected.getIntroduction()).isEqualTo(actual.getIntroduction()),
            () -> assertThat(expected.getRecruitmentCnt()).isEqualTo(actual.getRecruitmentCnt()),
            () -> assertThat(expected.getStartDate()).isEqualTo(actual.getStartDate()),
            () -> assertThat(expected.isOffline()).isEqualTo(actual.isOffline()),
            () -> assertThat(expected.getUniversity()).isEqualTo(actual.getUniversity()),
            () -> assertThat(expected.getLocation().getCity()).isEqualTo(actual.getLocation().getCity()),
            () -> assertThat(expected.getLocation().getDistrict()).isEqualTo(actual.getLocation().getDistrict())
        );
    }

    @Test
    @DisplayName("해당 모임의 관리자인지 확인한다")
    void validateManager_Success() {
        User user = getUserWithId();
        Group group = getGroupWithId(user);

        boolean expected = group.isManager(user);

        assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("해당 모임의 관리자를 변경한다")
    void updateManager_Success() {
        User user1 = getUserWithId();
        User user2 = getUserWithId();
        Group group = getGroupWithId(user1);

        group.updateManager(user2);

        assertThat(group.getManager().getId()).isEqualTo(user2.getId());
    }

    @Test
    @DisplayName("모임을 종료한다")
    void endGroup_Success() {
        Group expected = getGroupWithId(getUserWithId());

        expected.end();

        assertThat(expected.isEnd()).isTrue();
    }
}
