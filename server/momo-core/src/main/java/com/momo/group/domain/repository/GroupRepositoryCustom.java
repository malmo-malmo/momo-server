package com.momo.group.domain.repository;

import com.momo.district.entity.City;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.group.application.dto.request.GroupSearchConditionRequest;
import com.momo.group.domain.category.Category;
import com.momo.group.domain.Group;
import com.momo.user.domain.User;
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
