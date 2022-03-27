package com.momo.chat.domain.repository;

import com.momo.chat.domain.entity.Message;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {

    List<Message> findByChatIdOrderByRegDatetimeAsc(Long chatId);

    Optional<Message> findTop1ByChatIdOrderByRegDatetimeDesc(Long chatId);
}
