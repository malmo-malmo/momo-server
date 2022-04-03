package com.momo.chat.domain.service;


import com.momo.user.domain.model.User;

public interface ApplyParticipantUseCase {

    void applyParticipant(Long chatId, User user);
}