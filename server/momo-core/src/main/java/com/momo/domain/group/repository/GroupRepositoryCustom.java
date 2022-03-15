package com.momo.domain.group.repository;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface GroupRepositoryCustom {

    List<Group> findGroupAndAchievementRateByUser(User loginUser);

    GroupResponse findDetailByGroupId(User loginUser, Long groupId);

    List<GroupCardResponse> findAllBySearchConditionOrderByCreatedDateDesc(
        User loginUser, GroupSearchConditionRequest request, Pageable pageable
    );

    List<GroupCardResponse> findAllByCitiesAndCategoriesOrderByCreatedDateDesc(
        User loginUser, List<City> cities, List<Category> categories, Pageable pageable
    );

    List<GroupCardResponse> findByUniversityOrderByIdDesc(
        User loginUser, String university, Long lastGroupId, int size
    );

    List<GroupCardResponse> findByDistrictOrderByIdDesc(
        User loginUser, String district, Long lastGroupId, int size
    );

    List<GroupCardResponse> findByCategoriesOrderByIdDesc(
        User loginUser, List<Category> categories, Long lastGroupId, int size
    );
}
