package com.momo.socket.chat.controller;

import com.momo.common.SocketTest;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;

@Slf4j
class TestStompFrameHandler implements StompFrameHandler {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Payload : " + payload);
    }
}

public class TestControllerTest extends SocketTest {

    private Logger log = LoggerFactory.getLogger(TestControllerTest.class);

    @Test
    void main() {
        StompSession session = connectDefaultSession();

        session.subscribe("/sub/test", new TestStompFrameHandler());
        log.info("Subscribed to /sub/test");

        session.send("/pub/test", null);
        log.info("Message Send to /pub/test");

        session.disconnect();
    }
}