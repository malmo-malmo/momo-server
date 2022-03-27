package com.momo.chat.domain.service;

import static com.momo.ChatFixture.getChatWithId;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.fixture.MessageFixture.getNormalMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.chat.domain.service.impl.FindChatMessagesService;
import com.momo.common.ServiceTest;
import com.momo.domain.chat.repository.ChatRepository;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("채팅 메시지 목록 조회 유스케이스 테스트")
class FindChatMessagesServiceTests extends ServiceTest {

    private FindChatMessagesUseCase useCase;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void before() {
        useCase = new FindChatMessagesService(chatRepository, messageRepository);
    }

    @Test
    @DisplayName("채팅 메시지 목록을 조회한다")
    void find() {
        //...given
        Long chatId = 1L;
        User user = getUserWithId();
        User manager = getUserWithId();
        Group group = getGroupWithId(manager);
        given(chatRepository.findById(chatId)).willReturn(Optional.of(
            getChatWithId(group, manager, user))
        );

        List<Message> messages = List.of(
            getNormalMessage(chatId, user.getId()),
            getNormalMessage(chatId, manager.getId())
        );

        given(messageRepository.findByChatIdOrderByRegDatetimeAsc(chatId)).willReturn(messages);

        //...when
        List<SendPublishMessageResponse> responses = useCase.findChatMessages(chatId, user);

        //...then
        assertThat(responses.size()).isEqualTo(2);
        assertThat(responses.get(0).getMessage()).isEqualTo(messages.get(0).getContent());
        assertThat(responses.get(0).getCreDatetime()).isNotNull();
        assertThat(responses.get(0).getUsername()).isEqualTo(user.getNickname());
        assertThat(responses.get(0).getProfileImageUrl()).isEqualTo(user.getImageUrl());
        assertThat(responses.get(1).getMessage()).isEqualTo(messages.get(1).getContent());
        assertThat(responses.get(1).getCreDatetime()).isNotNull();
        assertThat(responses.get(1).getUsername()).isEqualTo(manager.getNickname());
        assertThat(responses.get(1).getProfileImageUrl()).isEqualTo(manager.getImageUrl());
    }

    @Test
    @DisplayName("채팅방이 존재하지 않으면 실패한다")
    void empty_chat() {
        //...given
        Long chatId = 1L;
        User user = getUserWithId();

        //...when and then
        assertThatThrownBy(() -> useCase.findChatMessages(chatId, user))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.INVALID_INDEX_NUMBER.getMessage());
    }
}