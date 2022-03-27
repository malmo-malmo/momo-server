package com.momo.chat.api;

import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface FindChatMessagesApiSpec {

    @GetMapping("/api/chat/{chatId}/messages")
    ResponseEntity<List<SendPublishMessageResponse>> findChatMessages(@CurrentUser User user,
        @PathVariable Long chatId);
}