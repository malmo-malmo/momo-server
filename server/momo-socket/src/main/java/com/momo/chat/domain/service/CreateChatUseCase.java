package com.momo.chat.domain.service;

import com.momo.domain.user.entity.User;

public interface CreateChatUseCase {

    Long createChats(Long groupId, User user);
}