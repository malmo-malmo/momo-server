package com.momo.group.domain.repository;

import com.momo.district.entity.City;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.domain.category.Category;
import com.momo.user.domain.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CustomGroupSearchEngine {

    List<GroupCardResponse> searchByNameLikeAndCitiesAndCategories(
         String name, List<City> cities, List<Category> categories, User user, Pageable pageable
    );
}
