package com.momo;

import static com.momo.common.FixtureComponents.INCREASE_ID;

import com.momo.domain.chat.entity.Chat;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;

public class ChatFixture {

    public static Chat getChatWithId(Group group, User manager, User user) {
        INCREASE_ID++;
        return Chat.builder()
            .id(INCREASE_ID)
            .manager(manager)
            .user(user)
            .group(group)
            .build();
    }
}
