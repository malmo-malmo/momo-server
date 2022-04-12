package com.momo.chat.repository;

import com.momo.chat.entity.Chat;
import com.momo.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByUserOrManager(User user, User manager);
}
