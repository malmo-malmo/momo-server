package com.momo.domain.group.search;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CustomGroupSearchEngine {

    List<GroupCardResponse> searchByNameLikeAndCitiesAndCategories(
         String name, List<City> cities, List<Category> categories, User user, Pageable pageable
    );
}
