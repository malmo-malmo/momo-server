package com.momo.domain.group.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("참가자 레포지토리 테스트")
public class ParticipantRepositoryTest extends RepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;
    private User user;
    private Group group;
    private Participant participant;

    @BeforeEach
    void before() {
        user = save(
            User.builder()
                .provider(SocialProvider.KAKAO)
                .providerId("test")
                .refreshToken("refresh Token")
                .nickname("유저1")
                .imageUrl("이미지 URL")
                .city(City.SEOUL)
                .district("마포구")
                .university("한국대")
                .build()
        );
        group = save(Group.builder()
            .city(City.SEOUL)
            .district("마포")
            .imageUrl("이미지 URL")
            .introduction("안녕하세요")
            .university("한국대")
            .isOffline(false)
            .isEnd(false)
            .startDate(LocalDate.now())
            .name("모임 이름")
            .category(Category.LIFE)
            .manager(user)
            .build());
        participant = save(
            Participant.builder()
                .group(group)
                .user(user)
                .build()
        );
    }

    @Test
    void 참가자를_저장한다() {
        Participant participant = participantRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(participant).isEqualTo(this.participant),
            () -> assertThat(participant.getGroup()).isEqualTo(group),
            () -> assertThat(participant.getUser()).isEqualTo(user)
        );
    }

    @Test
    void 모임에_참가한_참가자_목록을_조회한다() {
        List<Participant> participants = participantRepository.findAllByGroup(group);
        assertThat(participants.size()).isEqualTo(1);
        Participant participant = participants.get(0);
        assertThat(participant).isEqualTo(this.participant);
    }

    @Test
    void 유저가_해당_모임에_참여하고_있는지_조회한다() {
        boolean isParticipant = participantRepository.existsByGroupAndUser(group, user);
        assertThat(isParticipant).isTrue();
    }

    @Test
    void 유저의_모임_참여를_취소한다() {
        participantRepository.deleteByGroupAndUser(group, user);
        List<Participant> participants = participantRepository.findAll();
        assertThat(participants.size()).isEqualTo(0);
    }

    @Test
    void 유저가_참여한_모임_수를_조회한다() {
        Long actual = participantRepository.countAllByUser(user);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void 유저가_참여_중인_모임_목록을_조회한다() {
        save(
            Participant.builder()
                .group(group)
                .user(save(User.builder().providerId("test").build()))
                .build()
        );

        List<ParticipatingGroupCardResponse> actual = participantRepository.findParticipatingGroupsByUser(user);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isEqualTo(group.getId()),
            () -> assertThat(actual.get(0).getName()).isEqualTo(group.getName()),
            () -> assertThat(actual.get(0).getImageUrl()).isEqualTo(group.getImageUrl()),
            () -> assertThat(actual.get(0).getStartDate()).isEqualTo(group.getStartDate()),
            () -> assertThat(actual.get(0).isOffline()).isEqualTo(group.isOffline()),
            () -> assertThat(actual.get(0).isEnd()).isEqualTo(group.isEnd()),
            () -> assertThat(actual.get(0).getParticipantCnt()).isEqualTo(2)
        );
    }

    @Test
    void 참여자ID로_참여자_목록을_조회한다() {
        Long id1 = save(
            Participant.builder()
                .group(group)
                .user(user)
                .build()
        ).getId();
        Long id2 = save(
            Participant.builder()
                .group(group)
                .user(user)
                .build()
        ).getId();
        Long id3 = save(
            Participant.builder()
                .group(group)
                .user(user)
                .build()
        ).getId();

        List<Long> list = List.of(id3, id2, id1);
        List<Participant> attendances = participantRepository.findAllByIdsAndUser(list, user);

        Assertions.assertAll(
            () -> assertThat(attendances.size()).isEqualTo(3),
            () -> assertThat(attendances.get(0).getId()).isEqualTo(id3),
            () -> assertThat(attendances.get(1).getId()).isEqualTo(id2),
            () -> assertThat(attendances.get(2).getId()).isEqualTo(id1)
        );
    }
}
