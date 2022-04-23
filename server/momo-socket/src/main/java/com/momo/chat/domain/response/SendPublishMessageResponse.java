package com.momo.chat.domain.response;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.entity.ChatMessageType;
import com.momo.user.domain.User;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SendPublishMessageResponse {

    private String message;
    private LocalDateTime creDatetime;
    private String username;
    private String profileImageUrl;
    private ChatMessageType messageType;
    private boolean isSystem;

    @Builder
    private SendPublishMessageResponse(String message, LocalDateTime creDatetime,
        String username, String profileImageUrl, ChatMessageType messageType,
        boolean isSystem) {
        this.message = message;
        this.creDatetime = creDatetime;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.messageType = messageType;
        this.isSystem = isSystem;
    }

    public static SendPublishMessageResponse from(ChatMessage chatMessage, String nickname,
        String profileImageUrl) {
        return SendPublishMessageResponse.builder()
            .message(chatMessage.getContent())
            .creDatetime(chatMessage.getRegDatetime())
            .username(nickname)
            .profileImageUrl(profileImageUrl)
            .messageType(chatMessage.getType())
            .build();
    }

    public static SendPublishMessageResponse from(ChatMessage chatMessage,
        Map<Long, User> userMap) {
        String nickname = null;
        String profileImage = null;
        if (!chatMessage.isSystem()) {
            User user = userMap.get(chatMessage.getUserId());
            nickname = user.getNickname();
            profileImage = user.getImageUrl();
        }

        return SendPublishMessageResponse.builder()
            .message(chatMessage.getContent())
            .creDatetime(chatMessage.getRegDatetime())
            .username(nickname)
            .profileImageUrl(profileImage)
            .isSystem(chatMessage.isSystem())
            .messageType(chatMessage.getType())
            .build();
    }
}
