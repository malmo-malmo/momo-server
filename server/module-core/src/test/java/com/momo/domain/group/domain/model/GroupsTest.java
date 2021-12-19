package com.momo.domain.group.domain.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.momo.domain.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Groups POJO 테스트")
public class GroupsTest {
    private Groups groups;

    @BeforeEach
    void setup() {
        groups = Groups.builder()
                .id(1l)
                .manager(User.builder().id(1l).build())
                .build();
    }
    @Test
    @DisplayName("Groups 생성 테스트")
    void groupsTest() {
        assertThat(groups).isNotNull();
    }
    @Test
    @DisplayName("해당 사용자가 그룹 담당자인지 체크 테스트")
    void isManagerTest() {
        boolean isManager = groups.isManager(User.builder().id(1l).build());
        assertThat(isManager).isTrue();
    }
    @Test
    @DisplayName("그룹 담당자 변경 테스트")
    void updateManagerTest() {
        groups.updateManager(User.builder().id(2l).build());
        assertThat(groups.getManager().getId()).isEqualTo(2l);
    }
    @Test
    @DisplayName("그룹 종료 테스트")
    void endGroupTest() {
        groups.endGroup();
        assertThat(groups.isEnd()).isTrue();
    }
}
