package com.momo.chat.domain.mapper;

import com.momo.chat.domain.request.MessageDto;
import com.momo.chat.domain.request.SendDto;
import com.momo.chat.domain.entity.ChatMessageType;
import com.momo.user.domain.User;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SaveMessageMapper {

    public SendDto mapEntityToDto(MessageDto message, User user) {
        return SendDto.builder()
            .messageDto(message)
            .nickname(user.getNickname())
            .imageUrl(user.getImageUrl())
            .build();
    }
    public MessageDto mapToEntity(Long chatId, Long userId, String message) {
        return MessageDto.builder()
            .chatId(chatId)
            .userId(userId)
            .content(message)
            .regDatetime(LocalDateTime.now())
            .type(ChatMessageType.NORMAL)
            .build();
    }
}
