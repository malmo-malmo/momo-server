package com.momo.chat.api.impl;

import com.momo.chat.api.ApplyParticipantApiSpec;
import com.momo.chat.domain.service.ApplyParticipantUseCase;
import com.momo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class ApplyParticipantController implements ApplyParticipantApiSpec {

    private final ApplyParticipantUseCase service;

    @Override
    public void applyParticipantByGroup(User user, Long chatId) {
        service.applyParticipant(chatId, user);
    }
}
