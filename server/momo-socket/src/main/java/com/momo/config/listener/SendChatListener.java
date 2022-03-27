package com.momo.config.listener;

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
        System.out.println(new String(message.getBody()));
        SendDto dto = (SendDto) messageConverter.fromMessage(message);

        messageRepository.save(dto.getMessage());
        messageTemplate.convertAndSend("/sub/chat/" + dto.getMessage().getChatId(),
            SendPublishMessageResponse.from(dto.getMessage(), dto.getNickname(),
                dto.getImageUrl()));
    }
}
