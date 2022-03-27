package com.momo.chat.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.momo.chat.domain.request.SendMessageRequest;
import com.momo.config.model.SocketPrincipal;
import org.springframework.messaging.handler.annotation.MessageMapping;

public interface SaveMessageSocketSpec {

    @MessageMapping("/chat")
    void sendMessage(SendMessageRequest request, SocketPrincipal principal)
        throws JsonProcessingException;
}