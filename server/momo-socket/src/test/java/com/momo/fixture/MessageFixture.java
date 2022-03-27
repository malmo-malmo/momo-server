package com.momo.fixture;

import static com.momo.common.FixtureComponents.INCREASE_ID;

import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.entity.MessageType;
import java.time.LocalDateTime;

public class MessageFixture {

    public static Message getNormalMessage(Long chatId, Long userId) {
        INCREASE_ID++;
        return Message.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(MessageType.NORMAL)
            .content("test Message" + INCREASE_ID)
            .userId(userId)
            .build();
    }

    public static Message getStartMessage(Long chatId) {
        return Message.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(MessageType.START)
            .build();
    }

    public static Message getApproveMessage(Long chatId) {
        return Message.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(MessageType.APPROVE)
            .build();
    }
}