package com.momo.domain.group.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.management.dto.ParticipationGroupCardResponse;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("참여자 레포지토리 테스트")
public class ParticipantRepositoryTest extends RepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;

    private User user1;
    private Group group1;
    private Participant participant1;

    private User user2;
    private Group group2;
    private Participant participant2;

    @BeforeEach
    void before() {
        user1 = save(
            User.builder()
                .providerId("test")
                .nickname("유저1")
                .build()
        );
        group1 = save(
            Group.builder()
                .name("모임 이름1")
                .manager(user1)
                .build()
        );
        participant1 = save(
            Participant.builder()
                .group(group1)
                .user(user1)
                .build()
        );
        user2 = save(
            User.builder()
                .providerId("test")
                .nickname("유저2")
                .build()
        );
        group2 = save(
            Group.builder()
                .name("모임 이름2")
                .manager(user2)
                .build()
        );
        participant2 = save(
            Participant.builder()
                .group(group2)
                .user(user2)
                .build()
        );
    }

    @Test
    void 참여자를_저장한다() {
        Participant actual = participantRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(actual.getId()).isEqualTo(participant1.getId()),
            () -> assertThat(actual.getGroup().getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.getUser().getId()).isEqualTo(user1.getId())
        );
    }

    @Test
    void 모임의_참여자_목록을_조회한다() {
        List<Participant> actual = participantRepository.findAllByGroup(group1);

        Assertions.assertAll(
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isEqualTo(participant1.getId()),
            () -> assertThat(actual.get(0).getGroup().getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.get(0).getUser().getId()).isEqualTo(user1.getId())
        );
    }

    @Test
    void 유저가_해당_모임에_참여하고_있는지_조회한다() {
        boolean actual = participantRepository.existsByGroupAndUser(group1, user1);
        assertThat(actual).isTrue();
    }

    @Test
    void 모임을_탈퇴한다() {
        participantRepository.deleteByGroupAndUser(group1, user1);
        boolean actual = participantRepository.findById(participant1.getId()).isPresent();
        assertThat(actual).isFalse();
    }

    @Test
    void 유저가_참여한_모임_수를_조회한다() {
        Long actual = participantRepository.countAllByUser(user1);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void 유저가_참여_중인_모임_목록을_조회한다() {
        save(
            Participant.builder()
                .group(group1)
                .user(save(User.builder().providerId("test").build()))
                .build()
        );

        List<ParticipationGroupCardResponse> actual = participantRepository.findParticipationGroupsByUser(user1);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.get(0).getName()).isEqualTo(group1.getName()),
            () -> assertThat(actual.get(0).getImageUrl()).isEqualTo(group1.getImageUrl()),
            () -> assertThat(actual.get(0).getStartDate()).isEqualTo(group1.getStartDate()),
            () -> assertThat(actual.get(0).isOffline()).isEqualTo(group1.isOffline()),
            () -> assertThat(actual.get(0).isEnd()).isEqualTo(group1.isEnd()),
            () -> assertThat(actual.get(0).getParticipantCnt()).isEqualTo(2)
        );
    }

    @Test
    void 유저가_모임_관리자가_아닌_참여_중인_모임_목록을_조회한다() {
        save(
            Participant.builder()
                .group(group2)
                .user(user1)
                .build()
        );

        List<Participant> actual = participantRepository.findAllWithNotManagingGroupByUser(user1);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getGroup().getId()).isEqualTo(group2.getId())
        );
    }
}
