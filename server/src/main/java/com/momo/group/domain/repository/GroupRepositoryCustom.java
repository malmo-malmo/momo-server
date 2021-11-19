package com.momo.group.domain.repository;

import com.momo.group.domain.model.Category;
import com.momo.group.domain.model.Groups;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface GroupRepositoryCustom {

    List<Groups> findAllByCategoriesOrderByCreatedDateDesc(List<Category> categories, Pageable pageable);
}
