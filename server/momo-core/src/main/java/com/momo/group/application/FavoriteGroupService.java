package com.momo.group.application;

import com.momo.common.dto.EnumResponse;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.dto.request.FavoriteGroupCreateRequest;
import com.momo.group.application.dto.response.FavoriteGroupCardResponse;
import com.momo.group.application.dto.response.FavoriteGroupCountResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.favorite.FavoriteGroup;
import com.momo.group.domain.repository.FavoriteGroupRepository;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.user.domain.User;
import com.momo.user.domain.favorite.FavoriteCategories;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteGroupService {

    private final GroupRepository groupRepository;
    private final FavoriteGroupRepository favoriteGroupRepository;

    public void createFavoriteGroup(User user, FavoriteGroupCreateRequest request) {
        Group group = getGroupById(request.getGroupId());
        FavoriteGroup favoriteGroup = FavoriteGroup.create(user, group);
        favoriteGroupRepository.save(favoriteGroup);
    }

    @Transactional(readOnly = true)
    public FavoriteGroupCountResponse countFavoriteGroupsByUser(User user) {
        Long count = favoriteGroupRepository.countByUser(user);
        return FavoriteGroupCountResponse.of(count);
    }

    @Transactional(readOnly = true)
    public List<FavoriteGroupCardResponse> findFavoriteGroupsByUser(User user) {
        return favoriteGroupRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public void deleteFavoriteGroupByUserAndGroupId(User loginUser, Long groupId) {
        Group group = getGroupById(groupId);
        favoriteGroupRepository.deleteByUserAndGroup(loginUser, group);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
