package com.momo.group;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.SpringContainerTest;
import com.momo.domain.achievementrate.entity.ParticipantAchievementRate;
import com.momo.domain.achievementrate.repository.ParticipantAchievementRateRepository;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("참여자 리스너 테스트")
public class ParticipantListenerTest extends SpringContainerTest {

    @Autowired
    private ParticipantAchievementRateRepository participantAchievementRateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    private Participant participant;

    @BeforeEach
    void before() {
        User user = userRepository.save(
            User.builder()
                .providerId("test")
                .nickname("유저")
                .build()
        );
        Group group = groupRepository.save(
            Group.builder()
                .name("모임 이름")
                .manager(user)
                .build()
        );
        participant = participantRepository.save(
            Participant.builder()
                .group(group)
                .user(user)
                .build()
        );
    }

    @Test
    void 참여자를_저장하면_저장_리스너가_실행된다() {
        verifyParticipantPersistListener();
    }

    private void verifyParticipantPersistListener() {
        List<ParticipantAchievementRate> actual = participantAchievementRateRepository.findAll();
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isNotNull(),
            () -> assertThat(actual.get(0).getParticipant().getId()).isEqualTo(participant.getId()),
            () -> assertThat(actual.get(0).getRate()).isNotNull()
        );
    }
}
