package com.momo.chat.api.impl;

import com.momo.chat.api.FindChatsApiSpec;
import com.momo.chat.domain.response.ChatResponse;
import com.momo.chat.domain.service.FindChatsUseCase;
import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FindChatsController implements FindChatsApiSpec {

    private final FindChatsUseCase service;

    public ResponseEntity<List<ChatResponse>> findChats(@CurrentUser User user) {
        List<ChatResponse> responses = service.findChats(user);
        return ResponseEntity.ok(responses);
    }
}
