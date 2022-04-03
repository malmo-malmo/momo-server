package com.momo.chat.domain.service;


import com.momo.user.domain.model.User;

public interface CreateChatUseCase {

    Long createChats(Long groupId, User user);
}