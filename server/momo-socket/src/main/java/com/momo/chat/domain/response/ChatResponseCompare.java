package com.momo.chat.domain.response;

public class ChatResponseCompare {
    public static int compare(ChatResponse first, ChatResponse second) {
        if (first.getCreateDate().isBefore(second.getCreateDate())) {
            return 1;
        }
        return -1;
    }
}
