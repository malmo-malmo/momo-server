package com.momo.domain.group.repository;

import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface GroupRepositoryCustom {

    GroupResponse findGroupAndParticipantCntAndAuthorityById(User loginUser, Long groupId);

    List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(User loginUser,
        GroupSearchConditionRequest request, Pageable pageable);

    List<GroupCardResponse> findAllByUniversityOrderByCreatedDateDesc(User loginUser, String university,
        Pageable pageable);

    List<GroupCardResponse> findAllByDistrictOrderByCreatedDateDesc(User loginUser, String district, Pageable pageable);

    List<GroupCardResponse> findAllByCategoriesOrderByCreatedDateDesc(User loginUser, List<Category> categories,
        Pageable pageable);
}
