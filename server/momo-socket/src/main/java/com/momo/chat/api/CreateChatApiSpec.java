package com.momo.chat.api;

import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface CreateChatApiSpec {

    @PostMapping("/api/chat/group/{groupId}")
    ResponseEntity<Void> create(@CurrentUser User user, @PathVariable Long groupId)
        throws URISyntaxException;
}