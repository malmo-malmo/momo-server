package com.momo.chat.domain.service;

import com.momo.domain.user.entity.User;

public interface ApplyParticipantUseCase {

    void applyParticipant(Long chatId, User user);
}