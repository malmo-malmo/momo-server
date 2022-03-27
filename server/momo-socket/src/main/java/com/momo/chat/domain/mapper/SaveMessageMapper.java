package com.momo.chat.domain.mapper;

import com.momo.chat.domain.request.SendDto;
import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.entity.MessageType;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SaveMessageMapper {

    public SendDto mapEntityToDto(Message message, User user) {
        return SendDto.builder()
            .message(message)
            .nickname(user.getNickname())
            .imageUrl(user.getImageUrl())
            .build();
    }
    public Message mapToEntity(Long chatId, Long userId, String message) {
        return Message.builder()
            .chatId(chatId)
            .userId(userId)
            .content(message)
            .regDatetime(LocalDateTime.now())
            .type(MessageType.NORMAL)
            .build();
    }
}
