package com.momo;

import com.momo.favorite.dto.FavoriteGroupCardResponse;
import com.momo.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.favorite.entity.FavoriteGroup;
import com.momo.group.dto.GroupCardResponse;
import com.momo.group.entity.Group;
import com.momo.user.entity.User;

public class FavoriteFixture {

    public static FavoriteGroup getFavoriteGroup(User user, Group group) {
        return FavoriteGroup.builder()
            .user(user)
            .group(group)
            .build();
    }

    public static FavoriteGroupCreateRequest getFavoriteGroupCreateRequest(Long groupId) {
        return FavoriteGroupCreateRequest.builder()
            .groupId(groupId)
            .build();
    }

    public static FavoriteGroupCardResponse getFavoriteGroupCardResponse(GroupCardResponse groupCardResponse) {
        return FavoriteGroupCardResponse.builder()
            .id(1L)
            .groupCardResponse(groupCardResponse)
            .build();
    }
}
