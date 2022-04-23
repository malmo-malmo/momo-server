package com.momo.chat.domain.service;


import com.momo.user.domain.User;

public interface CreateChatUseCase {

    Long createChats(Long groupId, User user);
}
