package com.momo.chat.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.momo.user.domain.model.User;

public interface SaveMessageUseCase {

    void saveMessage(Long chatId, String message, User user) throws JsonProcessingException;
}