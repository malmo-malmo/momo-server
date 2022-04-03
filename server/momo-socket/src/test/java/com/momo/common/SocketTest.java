package com.momo.common;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.common.acceptance.AcceptanceTest;
import com.momo.config.SocketConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

public class SocketTest extends AcceptanceTest {

    private Logger log = LoggerFactory.getLogger(SocketTest.class);
    @LocalServerPort
    private Integer port;

    private static final String BASE_URL = "ws://localhost:%d" + SocketConfig.END_POINT;

    protected BlockingQueue<SendPublishMessageResponse> blockingQueue;

    private String testUrl;

    private WebSocketStompClient webSocketStompClient;
    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        webSocketStompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        testUrl = String.format(BASE_URL, port);
    }

    protected StompSession connectDefaultSession(StompHeaders headers) {
        StompSession stompSession;

        try {
            stompSession = connectSession(headers);
        } catch (Exception e) {
            log.error("SocketSessionConnectException {}", e);
            stompSession = null;
        }
        return stompSession;
    }

    protected StompSession connectSession(StompHeaders headers)
        throws ExecutionException, InterruptedException, TimeoutException {
        return webSocketStompClient.connect(testUrl, new WebSocketHttpHeaders(), headers,
            new StompSessionHandlerAdapter() {
            }).get(1, SECONDS);
    }

    protected StompHeaders createHeaders(String userToken, Long chatId) {
        StompHeaders headers = new StompHeaders();
        headers.add("Authorization", userToken);
        headers.add("chat-id", String.valueOf(chatId));
        return headers;
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }
}