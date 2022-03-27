package com.momo.chat.domain.request;

import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.entity.MessageType;
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

    public Message toEntity(Long userId) {
        return Message.builder()
            .chatId(this.chatId)
            .userId(userId)
            .content(this.message)
            .regDatetime(LocalDateTime.now())
            .type(MessageType.NORMAL)
            .build();
    }
}
