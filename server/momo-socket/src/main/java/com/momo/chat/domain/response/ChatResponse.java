package com.momo.chat.domain.response;

import com.momo.chat.domain.entity.Message;
import com.momo.domain.common.util.TimeFormatUtil;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatResponse {

    private Long chatId;
    private String profileImageUrl;
    private String username;
    private String lastMessage;
    private LocalDateTime createDate;
    private String createDateMessage;

    @Builder
    public ChatResponse(Long chatId, String profileImageUrl, String username,
        String lastMessage, LocalDateTime createDate, String createDateMessage) {
        this.chatId = chatId;
        this.profileImageUrl = profileImageUrl;
        this.username = username;
        this.lastMessage = lastMessage;
        this.createDate = createDate;
        this.createDateMessage = createDateMessage;
    }

    public static ChatResponse from(Message message, String nickname, String imageUrl) {
        return ChatResponse.builder()
            .chatId(message.getChatId())
            .profileImageUrl(imageUrl)
            .username(nickname)
            .lastMessage(message.getContent())
            .createDate(message.getRegDatetime())
            .createDateMessage(TimeFormatUtil.generateDateInfo(message.getRegDatetime()))
            .build();
    }
}
