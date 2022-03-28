package com.momo.chat.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.TestProfile;
import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.entity.ChatMessageType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(TestProfile.TEST)
@DataMongoTest
@DisplayName("메시지 레포지토리 테스트")
public class ChatMessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    private ChatMessage saveChatMessage;
    private ChatMessage findChatMessage;

    @BeforeEach
    void setup() {
        messageRepository.deleteAll();
        saveChatMessage = messageRepository.save(ChatMessage.builder()
            .chatId(1L)
            .type(ChatMessageType.NORMAL)
            .userId(1L)
            .content("가입해도 되나요?")
            .regDatetime(LocalDateTime.now())
            .build());
    }

    @Test
    @DisplayName("메시지를 등록한다")
    void save() {
        findChatMessage = messageRepository.findById(saveChatMessage.getId()).get();
    }

    @Test
    @DisplayName("채팅방 메시지 목록을 조회한다")
    void findByChatIdOrderByRegDatetimeAsc() {
        findChatMessage = messageRepository.findByChatIdOrderByRegDatetimeAsc(1L).get(0);
    }

    @AfterEach
    void after() {
        assertThat(findChatMessage).isNotNull();
        assertThat(findChatMessage.getId()).isEqualTo(saveChatMessage.getId());
        assertThat(findChatMessage.getType()).isEqualTo(saveChatMessage.getType());
        assertThat(findChatMessage.getUserId()).isEqualTo(saveChatMessage.getUserId());
        assertThat(findChatMessage.getContent()).isEqualTo(saveChatMessage.getContent());

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        assertThat(findChatMessage.getRegDatetime().format(format))
            .isEqualTo(saveChatMessage.getRegDatetime().format(format));
    }
}
