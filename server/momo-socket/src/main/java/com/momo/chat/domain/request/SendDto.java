package com.momo.chat.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SendDto {

    private MessageDto messageDto;

    private String nickname;

    private String imageUrl;

    @Builder
    public SendDto(MessageDto messageDto, String nickname, String imageUrl) {
        this.messageDto = messageDto;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
