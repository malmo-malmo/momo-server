package com.momo.chat.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "messages")
@NoArgsConstructor
public class ChatMessage {

    @Id
    private String id;

    private Long chatId;

    @Enumerated(EnumType.STRING)
    private ChatMessageType type;

    private Long userId;

    private String content;

    private LocalDateTime regDatetime;

    @Builder
    public ChatMessage(String id, Long chatId, ChatMessageType type, Long userId, String content,
        LocalDateTime regDatetime) {
        this.id = id;
        this.chatId = chatId;
        this.type = type;
        this.userId = userId;
        this.content = content;
        this.regDatetime = regDatetime;
    }

    public static ChatMessage create(Long chatId, ChatMessageType type) {
        return ChatMessage.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(type)
            .build();
    }

    public boolean isSystem() {
        return type.isSystem();
    }
}