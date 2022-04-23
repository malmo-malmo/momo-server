package com.momo.chat.api.acceptance;

import static com.momo.GroupFixture.getGroup;
import static com.momo.UserFixture.getUser;
import static com.momo.chat.api.acceptance.step.ChatAcceptanceStep.requestCreateChat;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.momo.chat.domain.entity.ChatMessageType;
import com.momo.chat.domain.request.SendMessageRequest;
import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.common.SocketTest;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.user.domain.User;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.LinkedBlockingDeque;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;

@DisplayName("소켓 채팅 테스트")
public class ChatControllerSocketAcceptanceTest extends SocketTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    @DisplayName("채팅을 전송한다")
    void send() throws InterruptedException {
        blockingQueue = new LinkedBlockingDeque<>();
        User user = userRepository.save(getUser());
        String userToken = getAccessToken(user);
        User manager = userRepository.save(getUser());
        Long groupId = groupRepository.save(getGroup(manager)).getId();
        Long chatId = extractId(requestCreateChat(userToken, groupId));

        StompHeaders headers = createHeaders(userToken, chatId);
        StompSession userSession = connectDefaultSession(headers);

        userSession.subscribe("/sub/chat/1", new TestStompFrameHandler());

        SendMessageRequest message = SendMessageRequest.builder()
            .chatId(chatId)
            .message("모임에 가입할 수 있을까요?")
            .build();
        userSession.send("/pub/chat", message);

        SendPublishMessageResponse response = blockingQueue.poll(1, SECONDS);

        assertThat(response).isNotNull();
        assertAll(
            () -> assertThat(response.getUsername()).isEqualTo(user.getNickname()),
            () -> assertThat(response.getMessage()).isEqualTo(message.getMessage()),
            () -> assertThat(response.getCreDatetime()).isNotNull(),
            () -> assertThat(response.getMessageType()).isEqualTo(ChatMessageType.NORMAL),
            () -> assertThat(response.getProfileImageUrl()).isEqualTo(user.getImageUrl())
        );
    }

    class TestStompFrameHandler implements StompFrameHandler {

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            try {
                SendPublishMessageResponse response = objectMapper
                    .readValue((byte[]) payload, SendPublishMessageResponse.class);
                blockingQueue.add(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
