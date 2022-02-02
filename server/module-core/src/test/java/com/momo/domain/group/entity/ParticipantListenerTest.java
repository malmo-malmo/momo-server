package com.momo.domain.group.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.achievementrate.entity.ParticipantAchievementRate;
import com.momo.domain.achievementrate.repository.ParticipantAchievementRateRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.user.entity.User;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("참여자 리스너 테스트")
public class ParticipantListenerTest extends RepositoryTest {

    @Autowired
    private ParticipantAchievementRateRepository participantAchievementRateRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    private Participant participant;

    @BeforeEach
    void before() {
        User user = save(
            User.builder()
                .providerId("test")
                .nickname("유저")
                .build()
        );
        Group group = save(
            Group.builder()
                .name("모임 이름")
                .manager(user)
                .build()
        );
        participant = save(
            Participant.builder()
                .group(group)
                .user(user)
                .build()
        );
    }

    @Test
    void 참여자를_저장하면_저장_리스너가_실행된다() {
        verifyParticipantPersistListener();
        verifyExistsParticipantAchievementRateField();
    }

    private void verifyParticipantPersistListener() {
        List<ParticipantAchievementRate> actual = participantAchievementRateRepository.findAll();
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isNotNull(),
            () -> assertThat(actual.get(0).getRate()).isEqualTo(BigDecimal.ZERO)
        );
    }

    private void verifyExistsParticipantAchievementRateField() {
        Participant actual = participantRepository.findById(participant.getId()).get();
        ParticipantAchievementRate expected = participantAchievementRateRepository.findAll().get(0);

        assertThat(actual.getAchievementRate().getId()).isEqualTo(expected.getId());
    }
}
