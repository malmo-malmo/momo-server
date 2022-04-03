package com.momo.chat.domain.service;

import static com.momo.GroupFixture.getGroup;
import static com.momo.UserFixture.getUser;
import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.service.impl.CreateChatService;
import com.momo.chat.entity.Chat;
import com.momo.chat.repository.ChatRepository;
import com.momo.common.ServiceTest;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.entity.Group;
import com.momo.group.repository.GroupRepository;
import com.momo.user.domain.model.User;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("채팅방 생성 유스케이스 테스트")
class CreateChatServiceTests extends ServiceTest {

    private CreateChatUseCase useCase;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ChatRepository chatRepository;

    @BeforeEach
    void before() {
        useCase = new CreateChatService(messageRepository, groupRepository, chatRepository);
    }

    @Test
    @DisplayName("채팅방을 생성한다")
    void create() {
        //...given
        User manager = getUserWithId();
        Group group = getGroup(manager);
        Chat chat = Chat.builder().id(1L).build();

        given(groupRepository.findById(group.getId())).willReturn(Optional.of(group));
        given(chatRepository.save(any(Chat.class))).willReturn(chat);

        User user = getUserWithId();

        //...when
        Long chatId = useCase.createChats(group.getId(), user);

        //...then
        assertThat(chatId).isEqualTo(chat.getId());
        verify(messageRepository).save(any(ChatMessage.class));
    }

    @Test
    @DisplayName("모임 관리자는 채팅방 생성에 실패한다")
    void manager_create_fail() {
        //...given
        User manager = getUserWithId();
        Group group = getGroup(manager);
        Chat chat = Chat.builder().id(1L).build();

        given(groupRepository.findById(group.getId())).willReturn(Optional.of(group));

        //...when and then
        Assertions.assertThatThrownBy(() -> useCase.createChats(group.getId(), manager))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_AUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("모임이 존재하지 않으면 실패한다")
    void empty_group_fail() {
        //...given
        User user = getUser();
        Group group = getGroup(user);

        //...when and then
        Assertions.assertThatThrownBy(() -> useCase.createChats(group.getId(), user))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.INVALID_INDEX_NUMBER.getMessage());
    }
}