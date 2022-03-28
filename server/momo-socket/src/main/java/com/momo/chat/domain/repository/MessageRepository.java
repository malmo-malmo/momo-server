package com.momo.chat.domain.repository;

import com.momo.chat.domain.entity.ChatMessage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<ChatMessage, String> {

    List<ChatMessage> findByChatIdOrderByRegDatetimeAsc(Long chatId);

    Optional<ChatMessage> findTop1ByChatIdOrderByRegDatetimeDesc(Long chatId);
}
