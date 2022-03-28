package com.momo.chat.domain.service.impl;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.entity.ChatMessageType;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.service.CreateChatUseCase;
import com.momo.domain.chat.entity.Chat;
import com.momo.domain.chat.repository.ChatRepository;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateChatService implements CreateChatUseCase {

    private final MessageRepository messageRepository;

    private final GroupRepository groupRepository;

    private final ChatRepository chatRepository;

    @Override
    public Long createChats(Long groupId, User user) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));

        if (group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_AUTHORIZED);
        }

        Long chatId = chatRepository.save(Chat.create(group, user)).getId();
        messageRepository.save(ChatMessage.create(chatId, ChatMessageType.START));

        return chatId;
    }
}