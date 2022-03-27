package com.momo.chat.api.impl;

import com.momo.chat.api.CreateChatApiSpec;
import com.momo.chat.domain.service.CreateChatUseCase;
import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class CreateChatController implements CreateChatApiSpec {

    private final CreateChatUseCase service;

    public ResponseEntity<Void> create(@CurrentUser User user, @PathVariable Long groupId)
        throws URISyntaxException {
        Long chatId = service.createChats(groupId, user);
        return ResponseEntity.created(new URI("/api/chat/group/" + chatId)).build();
    }
}