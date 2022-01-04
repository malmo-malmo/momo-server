package com.momo.domain.group.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Groups;
import com.momo.domain.group.entity.Participant;
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

    private Groups group;

    private Participant participant;

    @BeforeEach
    public void before() {
        user = save(
            User.builder()
                .provider(SocialProvider.KAKAO)
                .providerId("test")
                .refreshToken("refresh Token")
                .nickname("testMan")
                .imageUrl("http://~~")
                .city("서울")
                .district("마포구")
                .university("한국대")
                .build()
        );
        group = save(Groups.builder()
            .city("서울")
            .district("마포")
            .imageUrl("http://~")
            .introduction("안녕하세요")
            .university("한국대")
            .isOffline(false)
            .isEnd(false)
            .startDate(LocalDate.now())
            .name("모임 이름")
            .category(Category.LIFE)
            .manager(user)
            .build());
        participant = save(Participant.builder()
            .group(group)
            .user(user)
            .build());
    }

    @Test
    public void 참가자를_저장한다() {
        Participant participant = participantRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(participant).isEqualTo(this.participant),
            () -> assertThat(participant.getGroup()).isEqualTo(group),
            () -> assertThat(participant.getUser()).isEqualTo(user)
        );
    }

    @Test
    public void 모임에_참가한_참가자_목록을_조회한다() {
        List<Participant> participants = participantRepository.findAllByGroup(group);

        assertThat(participants.size()).isEqualTo(1);

        Participant participant = participants.get(0);
        assertThat(participant).isEqualTo(this.participant);
    }

    @Test
    public void 유저가_해당_모임에_참여하고_있는지_조회한다() {
        boolean isParticipant = participantRepository.existsByGroupAndUser(group, user);

        assertThat(isParticipant).isTrue();
    }

    @Test
    public void 유저의_모임_참여를_취소한다() {
        participantRepository.deleteByGroupAndUser(group, user);

        List<Participant> participants = participantRepository.findAll();
        assertThat(participants.size()).isEqualTo(0);
    }
}
