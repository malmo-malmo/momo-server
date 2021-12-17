package com.momo.domain.group.domain.repository;

import java.util.List;

import com.momo.domain.group.domain.model.Category;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.user.domain.model.User;
import org.springframework.data.domain.Pageable;

public interface GroupRepositoryCustom {

    GroupResponse findGroupAndParticipantCntAndAuthorityById(User loginUser, Long groupId);

    List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(List<String> cities,
                                                                           List<Category> categories, Pageable pageable);

    List<GroupCardResponse> findAllByUniversityOrderByCreatedDateDesc(String university, Pageable pageable);

    List<GroupCardResponse> findAllByDistrictOrderByCreatedDateDesc(String district, Pageable pageable);

    List<GroupCardResponse> findAllByCategoriesOrderByCreatedDateDesc(List<Category> categories, Pageable pageable);
}
