package com.momo.chat.api;

import com.momo.chat.domain.response.ChatResponse;
import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface FindChatsApiSpec {

    @GetMapping("/api/chat/list")
    ResponseEntity<List<ChatResponse>> findChats(@CurrentUser User user);
}