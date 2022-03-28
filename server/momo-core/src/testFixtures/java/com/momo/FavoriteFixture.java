package com.momo;

import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.domain.favorite.entity.FavoriteGroup;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.domain.User;

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
