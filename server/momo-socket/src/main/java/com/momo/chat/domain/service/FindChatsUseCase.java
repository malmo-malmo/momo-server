package com.momo.chat.domain.service;

import com.momo.chat.domain.response.ChatResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface FindChatsUseCase {

    List<ChatResponse> findChats(User user);
}