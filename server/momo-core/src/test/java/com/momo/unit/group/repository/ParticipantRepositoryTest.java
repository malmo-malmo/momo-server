package com.momo.unit.group.repository;

import static com.momo.GroupFixture.getGroup;
import static com.momo.ParticipantFixture.getParticipant;
import static com.momo.UserFixture.getUser;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.group.repository.ParticipantRepository;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.user.domain.model.User;
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
        user1 = save(getUser());
        group1 = save(getGroup(user1));
        participant1 = save(getParticipant(group1, user1));
        user2 = save(getUser());
        group2 = save(getGroup(user2));
        participant2 = save(getParticipant(group2, user2));
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
            () -> assertThat(actual.get(0).getParticipantCnt()).isEqualTo(1)
        );
    }

    @Test
    void 유저가_모임_관리자가_아닌_참여_중인_모임_목록을_조회한다() {
        save(getParticipant(group2, user1));

        List<Participant> actual = participantRepository.findAllWithNotManagingGroupByUser(user1);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getGroup().getId()).isEqualTo(group2.getId())
        );
    }

    @Test
    void 참여자ID로_참여자_목록을_조회한다() {
        Long id1 = save(getParticipant(group1, user1)).getId();
        Long id2 = save(getParticipant(group1, user1)).getId();
        Long id3 = save(getParticipant(group1, user1)).getId();

        List<Long> list = List.of(id3, id2, id1);
        List<Participant> attendances = participantRepository.findAllByIdsAndUser(list, group1);

        Assertions.assertAll(
            () -> assertThat(attendances.size()).isEqualTo(3),
            () -> assertThat(attendances.get(0).getId()).isEqualTo(id3),
            () -> assertThat(attendances.get(1).getId()).isEqualTo(id2),
            () -> assertThat(attendances.get(2).getId()).isEqualTo(id1)
        );
    }
}
