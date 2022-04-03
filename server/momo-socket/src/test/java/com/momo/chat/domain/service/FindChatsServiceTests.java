package com.momo.chat.domain.service;

import static com.momo.ChatFixture.getChatWithId;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.common.util.TimeFormatUtil.generateDateInfo;
import static com.momo.fixture.MessageFixture.getNormalMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.response.ChatResponse;
import com.momo.chat.domain.service.impl.FindChatsService;
import com.momo.chat.entity.Chat;
import com.momo.chat.repository.ChatRepository;
import com.momo.common.ServiceTest;
import com.momo.group.entity.Group;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("채팅방 목록 조회 유스케이스 테스트")
class FindChatsServiceTests extends ServiceTest {

    private FindChatsUseCase useCase;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void before() {
        useCase = new FindChatsService(
            chatRepository,
            userRepository,
            messageRepository
        );
    }

    @Test
    @DisplayName("채팅방 목록을 조회한다")
    void find() {
        //...given
        User manager = getUserWithId();
        User user = getUserWithId();
        Group group = getGroupWithId(manager);

        Chat chat = getChatWithId(group, manager, user);
        ChatMessage chatMessage = getNormalMessage(chat.getId(), manager.getId());

        given(chatRepository.findAllByUserOrManager(eq(manager), eq(manager)))
            .willReturn(List.of(chat));
        given(messageRepository.findTop1ByChatIdOrderByRegDatetimeDesc(chat.getId()))
            .willReturn(Optional.of(chatMessage));

        //...when
        List<ChatResponse> responses = useCase.findChats(manager);

        //...then
        assertThat(responses.size()).isEqualTo(1);
        ChatResponse response = responses.get(0);
        assertThat(response.getChatId()).isEqualTo(chat.getId());
        assertThat(response.getUsername()).isEqualTo(manager.getNickname());
        assertThat(response.getProfileImageUrl()).isEqualTo(manager.getImageUrl());
        assertThat(response.getLastMessage()).isEqualTo(chatMessage.getContent());
        assertThat(response.getCreateDateMessage())
            .isEqualTo(generateDateInfo(chatMessage.getRegDatetime()));
    }
}