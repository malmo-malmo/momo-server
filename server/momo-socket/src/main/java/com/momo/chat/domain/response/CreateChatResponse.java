package com.momo.chat.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatResponse {

    private Long chatId;

    public static CreateChatResponse from(Long chatId) {
        return new CreateChatResponse(chatId);
    }
}
