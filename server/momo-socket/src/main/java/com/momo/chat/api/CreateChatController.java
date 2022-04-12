package com.momo.chat.api;

import com.momo.chat.domain.service.CreateChatUseCase;
import com.momo.common.auth.CurrentUser;
import com.momo.user.domain.User;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateChatController {

    private final CreateChatUseCase service;

    @PostMapping("/api/chat/group/{groupId}")
    public ResponseEntity<Void> create(@CurrentUser User user, @PathVariable Long groupId)
        throws URISyntaxException {
        Long chatId = service.createChats(groupId, user);
        return ResponseEntity.created(new URI("/api/chat/group/" + chatId)).build();
    }
}
