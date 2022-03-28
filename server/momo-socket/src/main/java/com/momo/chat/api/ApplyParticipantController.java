package com.momo.chat.api;

import com.momo.chat.domain.service.ApplyParticipantUseCase;
import com.momo.common.auth.CurrentUser;
import com.momo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public
class ApplyParticipantController {

    private final ApplyParticipantUseCase service;

    @PostMapping("/api/group/apply-participant/{chatId}")
    public void applyParticipantByGroup(@CurrentUser User user, @PathVariable Long chatId) {
        service.applyParticipant(chatId, user);
    }
}
