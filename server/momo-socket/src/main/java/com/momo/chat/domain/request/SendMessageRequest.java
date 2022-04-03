package com.momo.chat.domain.request;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.entity.ChatMessageType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendMessageRequest {

    private Long chatId;
    private String message;

    @Builder
    public SendMessageRequest(Long chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }

    public ChatMessage toEntity(Long userId) {
        return ChatMessage.builder()
            .chatId(this.chatId)
            .userId(userId)
            .content(this.message)
            .regDatetime(LocalDateTime.now())
            .type(ChatMessageType.NORMAL)
            .build();
    }
}
