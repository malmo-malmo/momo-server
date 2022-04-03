package com.momo.chat.api;

import com.momo.chat.domain.response.ChatResponse;
import com.momo.chat.domain.service.FindChatsUseCase;
import com.momo.common.auth.CurrentUser;
import com.momo.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FindChatsController {

    private final FindChatsUseCase service;

    @GetMapping("/api/chat/list")
    public ResponseEntity<List<ChatResponse>> findChats(@CurrentUser User user) {
        List<ChatResponse> responses = service.findChats(user);
        return ResponseEntity.ok(responses);
    }
}
