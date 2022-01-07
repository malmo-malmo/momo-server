package com.momo.domain.user.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.user.dto.FavoriteGroupCardResponse;
import com.momo.domain.user.dto.FavoriteGroupCountResponse;
import com.momo.domain.user.dto.FavoriteGroupCreateRequest;
import com.momo.domain.user.entity.FavoriteGroup;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.FavoriteGroupRepository;
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

    public void create(User user, FavoriteGroupCreateRequest request) {
        Group group = getGroupById(request.getGroupId());
        FavoriteGroup favoriteGroup = FavoriteGroup.create(user, group);
        favoriteGroupRepository.save(favoriteGroup);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    @Transactional(readOnly = true)
    public FavoriteGroupCountResponse count(User user) {
        Long count = favoriteGroupRepository.countByUser(user);
        return FavoriteGroupCountResponse.of(count);
    }

    @Transactional(readOnly = true)
    public List<FavoriteGroupCardResponse> findAll(User user) {
        return favoriteGroupRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    public void delete(Long id) {
        favoriteGroupRepository.deleteById(id);
    }
}
