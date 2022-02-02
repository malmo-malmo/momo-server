package com.momo.domain.group.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.achievementrate.entity.GroupAchievementRate;
import com.momo.domain.achievementrate.repository.GroupAchievementRateRepository;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.user.entity.User;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("모임 리스너 테스트")
public class GroupListenerTest extends RepositoryTest {

    @Autowired
    private GroupAchievementRateRepository groupAchievementRateRepository;

    @Autowired
    private GroupRepository groupRepository;

    private Group group;

    @BeforeEach
    void before() {
        User user = save(
            User.builder()
                .providerId("test")
                .nickname("유저")
                .build()
        );
        group = save(
            Group.builder()
                .name("모임 이름")
                .manager(user)
                .build()
        );
    }

    @Test
    void 모임을_저장하면_저장_리스너가_실행된다() {
        verifyGroupPersistListener();
        verifyExistsGroupAchievementRateField();
    }

    private void verifyGroupPersistListener() {
        List<GroupAchievementRate> actual = groupAchievementRateRepository.findAll();
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isNotNull(),
            () -> assertThat(actual.get(0).getRate()).isEqualTo(BigDecimal.ZERO)
        );
    }

    private void verifyExistsGroupAchievementRateField() {
        Group actual = groupRepository.findById(group.getId()).get();
        GroupAchievementRate expected = groupAchievementRateRepository.findAll().get(0);

        assertThat(actual.getAchievementRate().getId()).isEqualTo(expected.getId());
    }
}
