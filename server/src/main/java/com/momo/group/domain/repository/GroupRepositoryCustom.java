package com.momo.group.domain.repository;

import com.momo.group.controller.dto.GroupCardResponse;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.group.domain.model.Category;
import com.momo.user.domain.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface GroupRepositoryCustom {

    GroupResponse findGroupAndParticipantCntAndAuthorityById(User loginUser, Long groupId);

    List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(List<String> cities,
        List<Category> categories, Pageable pageable);

    List<GroupCardResponse> findAllByUniversityOrderByCreatedDateDesc(String university, Pageable pageable);

    List<GroupCardResponse> findAllByDistrictOrderByCreatedDateDesc(String district, Pageable pageable);

    List<GroupCardResponse> findAllByCategoriesOrderByCreatedDateDesc(List<Category> categories, Pageable pageable);
}
