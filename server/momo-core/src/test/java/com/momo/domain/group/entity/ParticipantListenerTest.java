package com.momo.domain.group.entity;

import static com.momo.GroupFixture.getGroup;
import static com.momo.ParticipantFixture.getParticipant;
import static com.momo.UserFixture.getUser;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.achievementrate.entity.ParticipantAchievementRate;
import com.momo.achievementrate.repository.ParticipantAchievementRateRepository;
import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.group.repository.ParticipantRepository;
import com.momo.user.domain.model.User;
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
        User user = save(getUser());
        Group group = save(getGroup(user));
        participant = save(getParticipant(group, user));
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
