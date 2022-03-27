package com.momo.chat.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
public class Message {

    @Id
    private String id;
    private Long chatId;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private Long userId;
    private String content;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime regDatetime;

    @Builder
    public Message(String id, Long chatId, MessageType type, Long userId, String content,
        LocalDateTime regDatetime) {
        this.id = id;
        this.chatId = chatId;
        this.type = type;
        this.userId = userId;
        this.content = content;
        this.regDatetime = regDatetime;
    }

    public static Message create(Long chatId, MessageType type) {
        return Message.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(type)
            .build();
    }

    public boolean isSystem() {
        return !type.equals(MessageType.NORMAL);
    }
}