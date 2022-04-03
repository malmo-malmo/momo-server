package com.momo.chat.domain.service.impl;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.entity.ChatMessageType;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.service.ApplyParticipantUseCase;
import com.momo.chat.entity.Chat;
import com.momo.chat.repository.ChatRepository;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.entity.Participant;
import com.momo.group.repository.ParticipantRepository;
import com.momo.user.domain.model.User;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyParticipantService implements ApplyParticipantUseCase {

    private final ChatRepository chatRepository;

    private final ParticipantRepository participantRepository;

    private final MessageRepository messageRepository;

    @Override
    public void applyParticipant(Long chatId, User user) {
        Chat chat = chatRepository.findById(chatId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));

        if (chat.getManager() != user) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
        int participantCnt = participantRepository.findAllByGroup(chat.getGroup()).size();
        int maxParticipantCnt = chat.getGroup().getRecruitmentCnt();
        if (participantCnt == maxParticipantCnt) {
            throw new CustomException(ErrorCode.GROUP_OVER_CAPACITY);
        }
        participantRepository.save(Participant.create(chat.getUser(), chat.getGroup()));
        messageRepository.save(ChatMessage.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(ChatMessageType.APPROVE)
            .build());
    }
}