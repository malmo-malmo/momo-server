package com.momo.chat.domain.repository;

import com.momo.chat.domain.request.SendDto;

public interface RabbitMqRepository {

    void save(Long chatId, SendDto sendDto);
}

