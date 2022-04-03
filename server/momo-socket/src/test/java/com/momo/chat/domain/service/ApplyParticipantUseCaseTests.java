package com.momo.chat.domain.service;

import static com.momo.ChatFixture.getChatWithId;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.service.impl.ApplyParticipantService;
import com.momo.chat.entity.Chat;
import com.momo.chat.repository.ChatRepository;
import com.momo.common.ServiceTest;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.entity.Group;
import com.momo.group.entity.Participant;
import com.momo.group.repository.ParticipantRepository;
import com.momo.user.domain.model.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("모임 참여 승인 유스케이스 테스트")
public class ApplyParticipantUseCaseTests extends ServiceTest {

    private ApplyParticipantUseCase useCase;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void before() {
        this.useCase = new ApplyParticipantService(
            chatRepository,
            participantRepository,
            messageRepository
        );
    }

    @Test
    @DisplayName("모임 인원이 초과되어 실패한다")
    void over() {
        //...given
        User manager = getUserWithId();
        User user = getUserWithId();
        Group group = getGroupWithId(manager);
        Chat chat = getChatWithId(group, manager, user);

        given(chatRepository.findById(chat.getId())).willReturn(Optional.of(chat));

        List<Participant> participants = LongStream.range(0, 10)
            .mapToObj((i) -> Participant.builder().id(i).build())
            .collect(toList());

        given(participantRepository.findAllByGroup(group)).willReturn(participants);

        //...when and then
        assertThatThrownBy(() -> useCase.applyParticipant(chat.getId(), manager))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_OVER_CAPACITY.getMessage());
    }

    @Test
    @DisplayName("채팅방이 존재하지 않으면 실패한다")
    void empty_chat() {
        //...given
        User user = getUserWithId();

        //...when and then
        assertThatThrownBy(() -> useCase.applyParticipant(1L, user))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.INVALID_INDEX_NUMBER.getMessage());
    }
}