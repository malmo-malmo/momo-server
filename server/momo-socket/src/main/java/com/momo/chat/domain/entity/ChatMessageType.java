package com.momo.chat.domain.entity;

import lombok.Getter;

@Getter
public enum ChatMessageType {
    START,
    NORMAL,
    APPROVE;

    public boolean isSystem() {
        return this != NORMAL;
    }
}
