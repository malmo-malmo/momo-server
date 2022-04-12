package com.momo.chat.domain.service;

import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.user.domain.User;
import java.util.List;

public interface FindChatMessagesUseCase {

    List<SendPublishMessageResponse> findChatMessages(Long chatId, User user);
}
