package com.momo.chat.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.TestProfile;
import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.entity.MessageType;
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
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    private Message saveMessage;
    private Message findMessage;

    @BeforeEach
    void setup() {
        messageRepository.deleteAll();
        saveMessage = messageRepository.save(Message.builder()
            .chatId(1L)
            .type(MessageType.NORMAL)
            .userId(1L)
            .content("가입해도 되나요?")
            .regDatetime(LocalDateTime.now())
            .build());
    }

    @Test
    @DisplayName("메시지를 등록한다")
    void save() {
        findMessage = messageRepository.findById(saveMessage.getId()).get();
    }

    @Test
    @DisplayName("채팅방 메시지 목록을 조회한다")
    void findByChatIdOrderByRegDatetimeAsc() {
        findMessage = messageRepository.findByChatIdOrderByRegDatetimeAsc(1L).get(0);
    }

    @AfterEach
    void after() {
        assertThat(findMessage).isNotNull();
        assertThat(findMessage.getId()).isEqualTo(saveMessage.getId());
        assertThat(findMessage.getType()).isEqualTo(saveMessage.getType());
        assertThat(findMessage.getUserId()).isEqualTo(saveMessage.getUserId());
        assertThat(findMessage.getContent()).isEqualTo(saveMessage.getContent());

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        assertThat(findMessage.getRegDatetime().format(format))
            .isEqualTo(saveMessage.getRegDatetime().format(format));
    }
}
