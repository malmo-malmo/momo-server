package com.momo.user.application.dto.request;

import com.momo.group.domain.category.Category;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteCategoriesUpdateRequest {

    @Size(min = 1)
    @NotNull(message = "관심 카테고리는 필수 입력값입니다.")
    private List<Category> favoriteCategories;
}
