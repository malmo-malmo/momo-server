package com.momo.chat.domain.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.entity.ChatMessageType;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MessageDto {

    private String id;

    private Long chatId;

    private ChatMessageType type;

    private Long userId;

    private String content;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime regDatetime;

    @Builder
    public MessageDto(String id, Long chatId, ChatMessageType type, Long userId, String content,
        LocalDateTime regDatetime) {
        this.id = id;
        this.chatId = chatId;
        this.type = type;
        this.userId = userId;
        this.content = content;
        this.regDatetime = regDatetime;
    }

    public ChatMessage toEntity() {
        return ChatMessage.builder()
            .id(id)
            .type(type)
            .chatId(chatId)
            .userId(userId)
            .content(content)
            .regDatetime(regDatetime)
            .build();
    }
}