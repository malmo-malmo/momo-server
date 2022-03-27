package com.momo.chat.socket.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.momo.chat.socket.SaveMessageSocketSpec;
import com.momo.chat.domain.request.SendMessageRequest;
import com.momo.chat.domain.service.SaveMessageUseCase;
import com.momo.config.model.SocketPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class SaveMessageController implements SaveMessageSocketSpec {

    private final SaveMessageUseCase service;

    public void sendMessage(SendMessageRequest request, SocketPrincipal principal)
        throws JsonProcessingException {
        service.saveMessage(request.getChatId(), request.getMessage(), principal.getUser());
    }
}