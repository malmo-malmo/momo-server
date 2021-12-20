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
            .id(1L)
            .manager(User.builder().id(1L).build())
            .build();
    }

    @Test
    void 모임_생성_테스트() {
        assertThat(groups).isNotNull();
    }

    @Test
    void 모임_담당자_여부_확인_테스트() {
        boolean isManager = groups.isManager(User.builder().id(1L).build());
        assertThat(isManager).isTrue();
    }

    @Test
    void 모임_담당자_변경_테스트() {
        groups.updateManager(User.builder().id(2L).build());
        assertThat(groups.getManager().getId()).isEqualTo(2L);
    }

    @Test
    void 모임_종료_테스트() {
        groups.endGroup();
        assertThat(groups.isEnd()).isTrue();
    }
}
