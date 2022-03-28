package com.momo.fixture;

import static com.momo.common.FixtureComponents.INCREASE_ID;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.entity.ChatMessageType;
import java.time.LocalDateTime;

public class MessageFixture {

    public static ChatMessage getNormalMessage(Long chatId, Long userId) {
        INCREASE_ID++;
        return ChatMessage.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(ChatMessageType.NORMAL)
            .content("test Message" + INCREASE_ID)
            .userId(userId)
            .build();
    }

    public static ChatMessage getStartMessage(Long chatId) {
        return ChatMessage.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(ChatMessageType.START)
            .build();
    }

    public static ChatMessage getApproveMessage(Long chatId) {
        return ChatMessage.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(ChatMessageType.APPROVE)
            .build();
    }
}