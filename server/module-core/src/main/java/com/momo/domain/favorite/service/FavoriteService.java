package com.momo.domain.favorite.service;

import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.favorite.dto.FavoriteCategoriesUpdateRequest;
import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCountResponse;
import com.momo.domain.favorite.dto.FavoriteGroupCreateRequest;
import com.momo.domain.favorite.entity.FavoriteCategories;
import com.momo.domain.favorite.entity.FavoriteGroup;
import com.momo.domain.user.entity.User;
import com.momo.domain.favorite.repository.FavoriteGroupRepository;
import com.momo.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {

    private final GroupRepository groupRepository;
    private final FavoriteGroupRepository favoriteGroupRepository;
    private final UserRepository userRepository;

    public Long createFavoriteGroup(User user, FavoriteGroupCreateRequest request) {
        Group group = getGroupById(request.getGroupId());
        FavoriteGroup favoriteGroup = FavoriteGroup.create(user, group);
        return favoriteGroupRepository.save(favoriteGroup).getId();
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
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

    @Transactional(readOnly = true)
    public List<EnumResponse> findFavoriteCategoriesByUser(User loginUser) {
        FavoriteCategories favoriteCategories = loginUser.getFavoriteCategories();
        return EnumResponse.listOfFavoriteCategories(favoriteCategories);
    }

    public void updateFavoriteCategories(User loginUser, FavoriteCategoriesUpdateRequest request) {
        User user = findByUser(loginUser);
        user.updateFavoriteCategories(request.getFavoriteCategories());
    }

    public User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void deleteFavoriteGroupById(Long id) {
        favoriteGroupRepository.deleteById(id);
    }
}
