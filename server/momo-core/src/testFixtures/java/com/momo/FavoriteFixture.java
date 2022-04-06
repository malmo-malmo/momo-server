package com.momo;

import com.momo.group.application.dto.response.FavoriteGroupCardResponse;
import com.momo.group.application.dto.request.FavoriteGroupCreateRequest;
import com.momo.group.domain.favorite.FavoriteGroup;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.domain.Group;
import com.momo.user.domain.User;

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
