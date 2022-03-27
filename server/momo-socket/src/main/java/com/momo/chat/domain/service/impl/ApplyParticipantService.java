package com.momo.chat.domain.service.impl;

import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.entity.MessageType;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.service.ApplyParticipantUseCase;
import com.momo.domain.chat.entity.Chat;
import com.momo.domain.chat.repository.ChatRepository;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.user.entity.User;
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
        messageRepository.save(Message.builder()
            .chatId(chatId)
            .regDatetime(LocalDateTime.now())
            .type(MessageType.APPROVE)
            .build());
    }
}