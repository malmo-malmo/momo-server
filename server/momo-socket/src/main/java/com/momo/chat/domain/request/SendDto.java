package com.momo.chat.domain.request;

import com.momo.chat.domain.entity.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SendDto {

    private Message message;

    private String nickname;

    private String imageUrl;

    @Builder
    public SendDto(Message message, String nickname, String imageUrl) {
        this.message = message;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
