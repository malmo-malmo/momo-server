package com.momo.chat.domain.service.impl;

import static com.momo.config.listener.SendChatListener.LISTENER_ID;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateQueueService {

    private final AmqpAdmin admin;

    private final RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    private final String routingKey;

    public CreateQueueService(
        AmqpAdmin admin,
        RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry,
        @Value("${spring.rabbitmq.routing-key}") String routingKey
    ) {
        this.admin = admin;
        this.rabbitListenerEndpointRegistry = rabbitListenerEndpointRegistry;
        this.routingKey = routingKey;
    }

    public void createQueue(String chatQueueName) {
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
                log.error("CreateQueueException : ", e);
            }
        }
    }
}
