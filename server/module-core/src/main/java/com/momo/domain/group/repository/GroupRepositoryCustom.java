package com.momo.domain.group.repository;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface GroupRepositoryCustom {

    GroupResponse findGroupAndParticipantCntAndAuthorityById(User loginUser, Long groupId);

    List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(List<City> cities,
        List<Category> categories, Pageable pageable);

    List<GroupCardResponse> findAllByUniversityOrderByCreatedDateDesc(String university, Pageable pageable);

    List<GroupCardResponse> findAllByDistrictOrderByCreatedDateDesc(String district, Pageable pageable);

    List<GroupCardResponse> findAllByCategoriesOrderByCreatedDateDesc(List<Category> categories, Pageable pageable);
}
