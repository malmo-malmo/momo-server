package com.momo.chat.api;

import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface ApplyParticipantApiSpec {

    @PostMapping("/api/group/apply-participant/{chatId}")
    void applyParticipantByGroup(@CurrentUser User user, @PathVariable Long chatId);
}