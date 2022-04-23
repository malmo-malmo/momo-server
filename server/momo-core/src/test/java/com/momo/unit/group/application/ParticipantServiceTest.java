package com.momo.unit.group.application;

import static com.momo.AchievementRateFixture.getParticipantAchievementRateWithId;
import static com.momo.GroupFixture.getGroup;
import static com.momo.ParticipantFixture.getParticipantWithId;
import static com.momo.UserFixture.getUserWithId;
import static java.math.BigDecimal.ZERO;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.dto.response.ParticipantResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.group.application.ParticipantService;
import com.momo.user.domain.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("참여자 서비스 테스트")
public class ParticipantServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ParticipantRepository participantRepository;

    private ParticipantService participantService;

    private User manager;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        manager = getUserWithId();
        user1 = getUserWithId();
        user2 = getUserWithId();
        participantService = new ParticipantService(participantRepository, groupRepository);
    }

    @Test
    void 모임_참여자_목록_조회_테스트() {
        Group group = getGroup(manager);
        Participant participant1 = getParticipantWithId(group, user1, getParticipantAchievementRateWithId(ZERO));
        Participant participant2 = getParticipantWithId(group, user2, getParticipantAchievementRateWithId(ZERO));
        List<Participant> participants = List.of(participant1, participant2);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.findAllByGroup(any())).willReturn(participants);

        List<ParticipantResponse> actual = participantService.findByGroupId(manager, 1L);

        verify(groupRepository).findById(any());
        verify(participantRepository).findAllByGroup(any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2),
            () -> assertThat(actual.get(0).getParticipantId()).isEqualTo(participant1.getId()),
            () -> assertThat(actual.get(0).getUserId()).isEqualTo(user1.getId()),
            () -> assertThat(actual.get(0).getNickname()).isEqualTo(user1.getNickname()),
            () -> assertThat(actual.get(0).getImageUrl()).isEqualTo(user1.getImageUrl()),
            () -> assertThat(actual.get(0).getAttendanceRate()).isNotNull(),
            () -> assertThat(actual.get(1).getParticipantId()).isEqualTo(participant2.getId()),
            () -> assertThat(actual.get(1).getUserId()).isEqualTo(user2.getId()),
            () -> assertThat(actual.get(1).getNickname()).isEqualTo(user2.getNickname()),
            () -> assertThat(actual.get(1).getImageUrl()).isEqualTo(user2.getImageUrl()),
            () -> assertThat(actual.get(1).getAttendanceRate()).isNotNull()
        );
    }

    @Test
    void 모임_관리자가_아니면_참여자_목록_조회_테스트가_실패한다() {
        Group group = getGroup(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> participantService.findByGroupId(user1, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_탈퇴_테스트() {
        Group group = getGroup(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        participantService.withdrawByGroupId(user1, 1L);
        verify(groupRepository).findById(any());
    }

    @Test
    void 모임_관리자가_탈퇴를_시도하면_테스트가_실패한다() {
        Group group = getGroup(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> participantService.withdrawByGroupId(manager, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_WITHDRAW_NOT_ALLOW.getMessage());
    }
}
