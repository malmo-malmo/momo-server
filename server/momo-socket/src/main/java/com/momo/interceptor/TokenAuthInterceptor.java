package com.momo.interceptor;

import static com.momo.config.listener.SendChatListener.LISTENER_ID;
import static com.momo.domain.common.exception.ErrorCode.INVALID_INDEX_NUMBER;
import static com.momo.domain.common.exception.ErrorCode.INVALID_OAUTH_ACCESS_TOKEN;
import static org.springframework.messaging.simp.stomp.StompCommand.CONNECT;
import static org.springframework.messaging.simp.stomp.StompCommand.DISCONNECT;
import static org.springframework.messaging.support.MessageHeaderAccessor.getAccessor;
import static org.springframework.web.socket.WebSocketHttpHeaders.AUTHORIZATION;

import com.momo.config.model.SocketPrincipal;
import com.momo.domain.auth.service.OAuthService;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.user.entity.User;
import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenAuthInterceptor implements ChannelInterceptor {

    private final OAuthService authService;

    private final AmqpAdmin admin;

    private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    private final String routingKey;

    public TokenAuthInterceptor(
        OAuthService authService,
        AmqpAdmin admin,
        RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry,
        @Value("${spring.rabbitmq.routing-key}") String routingKey
    ) {
        this.authService = authService;
        this.admin = admin;
        this.rabbitListenerEndpointRegistry = rabbitListenerEndpointRegistry;
        this.routingKey = routingKey;
    }

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = generateStompAccessor(message);

        StompCommand stompCommand = Optional.ofNullable(accessor.getCommand())
            .orElse(DISCONNECT);

        boolean isStompConnect = stompCommand.equals(CONNECT);
        if (isStompConnect) {
            String bearerToken = getAccessToken(accessor);
            registerAccessUserInfo(accessor, bearerToken);
            Long chatId = getChatId(accessor);

            String chatQueueName = String.format("chat-%s", chatId);
            AbstractMessageListenerContainer container = (AbstractMessageListenerContainer)
                rabbitListenerEndpointRegistry.getListenerContainer(LISTENER_ID);

            boolean isExistsQueue = Arrays.asList(container.getQueueNames())
                .contains(chatQueueName);
            if (!isExistsQueue) {
                Queue queue = new Queue(chatQueueName);
                DirectExchange exchange = new DirectExchange(chatQueueName);
                Binding binding = new Binding(
                    chatQueueName,
                    DestinationType.QUEUE,
                    chatQueueName,
                    routingKey,
                    null
                );
                try {
                    admin.declareQueue(queue);
                    admin.declareExchange(exchange);
                    admin.declareBinding(binding);
                    container.addQueues(queue);
                } catch (Exception e) {
                    log.error("Error : ", e);
                }
            }
        }
        return message;
    }

    private String getAccessToken(StompHeaderAccessor accessor) {
        return Optional.ofNullable(accessor.getNativeHeader(AUTHORIZATION))
            .orElseThrow(() -> new CustomException(INVALID_OAUTH_ACCESS_TOKEN)).get(0);
    }

    private Long getChatId(StompHeaderAccessor accessor) {
        String chatId = Optional.ofNullable(accessor.getNativeHeader("chat-id"))
            .orElseThrow(() -> new CustomException(INVALID_INDEX_NUMBER)).get(0);
        return Long.parseLong(chatId);
    }

    private StompHeaderAccessor generateStompAccessor(Message<?> message) {
        return getAccessor(message, StompHeaderAccessor.class);
    }

    private void registerAccessUserInfo(StompHeaderAccessor accessor, String bearerToken) {
        User user = authService.findLoginUserByAccessToken(bearerToken);
        Principal principal = new SocketPrincipal(user);
        accessor.setUser(principal);
    }
}
