package com.momo.chat.domain.response;

import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.entity.MessageType;
import com.momo.domain.user.entity.User;
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
    private MessageType messageType;
    private boolean isSystem;

    @Builder
    private SendPublishMessageResponse(String message, LocalDateTime creDatetime,
        String username, String profileImageUrl, MessageType messageType, boolean isSystem) {
        this.message = message;
        this.creDatetime = creDatetime;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.messageType = messageType;
        this.isSystem = isSystem;
    }

    public static SendPublishMessageResponse from(Message message, String nickname,
        String profileImageUrl) {
        return SendPublishMessageResponse.builder()
            .message(message.getContent())
            .creDatetime(message.getRegDatetime())
            .username(nickname)
            .profileImageUrl(profileImageUrl)
            .messageType(message.getType())
            .build();
    }

    public static SendPublishMessageResponse from(Message message, Map<Long, User> userMap) {
        String nickname = null;
        String profileImage = null;
        if (!message.isSystem()) {
            User user = userMap.get(message.getUserId());
            nickname = user.getNickname();
            profileImage = user.getImageUrl();
        }

        return SendPublishMessageResponse.builder()
            .message(message.getContent())
            .creDatetime(message.getRegDatetime())
            .username(nickname)
            .profileImageUrl(profileImage)
            .isSystem(message.isSystem())
            .messageType(message.getType())
            .build();
    }
}
