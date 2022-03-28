package com.momo.interceptor;

import static com.momo.domain.common.exception.ErrorCode.INVALID_INDEX_NUMBER;
import static com.momo.domain.common.exception.ErrorCode.INVALID_OAUTH_ACCESS_TOKEN;
import static org.springframework.messaging.simp.stomp.StompCommand.CONNECT;
import static org.springframework.messaging.simp.stomp.StompCommand.DISCONNECT;
import static org.springframework.messaging.support.MessageHeaderAccessor.getAccessor;
import static org.springframework.web.socket.WebSocketHttpHeaders.AUTHORIZATION;

import com.momo.chat.domain.service.impl.CreateQueueService;
import com.momo.config.model.SocketPrincipal;
import com.momo.domain.auth.service.OAuthService;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.user.entity.User;
import java.security.Principal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthInterceptor implements ChannelInterceptor {

    private final OAuthService authService;

    private final CreateQueueService createQueueService;

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
            createQueueService.createQueue(chatQueueName);
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
