package com.momo.config;

import com.momo.config.model.SocketPrincipal;
import com.momo.domain.auth.service.OAuthService;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
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
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthInterceptor implements ChannelInterceptor {

    private final OAuthService authService;
    private static final String TOKEN_HEADER_NAME = "Authorization";

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = generateStompAccessor(message);

        StompCommand stompCommand = Optional.ofNullable(accessor.getCommand())
            .orElse(StompCommand.DISCONNECT);

        boolean isStompConnect = stompCommand.equals(StompCommand.CONNECT);
        if (isStompConnect) {
            String bearerToken = getAccessToken(accessor);
            registAccessUserInfo(accessor, bearerToken);
        }
        return message;
    }

    private String getAccessToken(StompHeaderAccessor accessor) {
        return Optional.ofNullable(accessor.getNativeHeader(TOKEN_HEADER_NAME))
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_OAUTH_ACCESS_TOKEN)).get(0);
    }

    private StompHeaderAccessor generateStompAccessor(Message<?> message) {
        return MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    }

    private void registAccessUserInfo(StompHeaderAccessor accessor, String bearerToken) {
        User user = authService.findLoginUserByAccessToken(bearerToken);
        Principal principal = new SocketPrincipal(user);
        accessor.setUser(new SocketPrincipal(User.builder().nickname("유저 이름").build()));
    }
}
