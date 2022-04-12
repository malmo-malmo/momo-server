package com.momo.chat.domain.service.impl;

import com.momo.chat.domain.mapper.SaveMessageMapper;
import com.momo.chat.domain.repository.RabbitMqRepository;
import com.momo.chat.domain.request.MessageDto;
import com.momo.chat.domain.request.SendDto;
import com.momo.chat.domain.service.SaveMessageUseCase;
import com.momo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveMessageService implements SaveMessageUseCase {

    private final RabbitMqRepository rabbitMqRepository;

    private final SaveMessageMapper saveMessageMapper;

    @Override
    public void saveMessage(Long chatId, String message, User user) {
        MessageDto msg = saveMessageMapper.mapToEntity(chatId, user.getId(), message);
        SendDto dto = saveMessageMapper.mapEntityToDto(msg, user);

        rabbitMqRepository.save(chatId, dto);
    }
}
