package com.momo.socket.chat.controller;

import com.momo.config.model.SocketPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final SimpMessageSendingOperations messageTemplate;

    @MessageMapping("/test")
    public void test(SocketPrincipal principal) {
        log.info("START ====");
        String usernmae = principal.getUser().getNickname();

        messageTemplate.convertAndSend("/sub/test", usernmae);
    }
}
