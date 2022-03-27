package com.momo.chat.domain.repository;

import com.momo.chat.domain.request.SendDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqRepositoryImpl implements RabbitMqRepository {

    private final RabbitTemplate rabbitTemplate;

    private final String routingKey;

    public RabbitMqRepositoryImpl(RabbitTemplate rabbitTemplate,
        @Value("${spring.rabbitmq.routing-key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.routingKey = routingKey;
    }

    public void save(Long chatId, SendDto sendDto) {
        String chatQueueName = String.format("chat-%d", chatId);
        rabbitTemplate.convertAndSend(chatQueueName, routingKey, sendDto);
    }
}
