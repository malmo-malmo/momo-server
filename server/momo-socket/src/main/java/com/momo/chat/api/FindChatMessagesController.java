package com.momo.chat.api;

import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.chat.domain.service.FindChatMessagesUseCase;
import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public
class FindChatMessagesController {

    private final FindChatMessagesUseCase service;

    @GetMapping("/api/chat/{chatId}/messages")
    public ResponseEntity<List<SendPublishMessageResponse>> findChatMessages(@CurrentUser User user,
        @PathVariable Long chatId) {
        List<SendPublishMessageResponse> responses = service.findChatMessages(chatId, user);
        return ResponseEntity.ok(responses);
    }
}