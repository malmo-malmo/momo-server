package com.momo.config.listener;

import com.momo.chat.domain.entity.ChatMessage;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.request.SendDto;
import com.momo.chat.domain.response.SendPublishMessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendChatListener {

    public static final String LISTENER_ID = "chat-receive";

    private final MessageRepository messageRepository;

    private final SimpMessageSendingOperations messageTemplate;

    private final MessageConverter messageConverter;

    @RabbitListener(id = LISTENER_ID)
    public void receiveMessage(final Message message) {
        SendDto dto = (SendDto) messageConverter.fromMessage(message);

        ChatMessage messageEntity = dto.getMessageDto().toEntity();
        messageRepository.save(messageEntity);

        String target = String.format("/sub/chat/%d", messageEntity.getChatId());
        messageTemplate.convertAndSend(target,
            SendPublishMessageResponse.from(
                messageEntity,
                dto.getNickname(),
                dto.getImageUrl()
            )
        );
    }
}
