package com.momo.chat.domain.service;

import static com.momo.UserFixture.getUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.momo.chat.domain.mapper.SaveMessageMapper;
import com.momo.chat.domain.repository.RabbitMqRepository;
import com.momo.chat.domain.request.SendDto;
import com.momo.chat.domain.service.impl.SaveMessageService;
import com.momo.common.ServiceTest;
import com.momo.user.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("메시지 전송 유스케이스 테스트")
class SaveChatMessageServiceTests extends ServiceTest {

    private SaveMessageUseCase useCase;

    @Mock
    private RabbitMqRepository rabbitMqRepository;

    @BeforeEach
    void before() {
        useCase = new SaveMessageService(
            rabbitMqRepository,
            new SaveMessageMapper()
        );
    }

    @Test
    @DisplayName("메시지를 전송한다")
    void send() throws JsonProcessingException {
        //...given
        User user = getUser();
        Long chatId = 1L;

        //...when
        useCase.saveMessage(chatId, "안녕하세요~", user);

        //...then
        verify(rabbitMqRepository).save(eq(chatId), any(SendDto.class));
    }
}