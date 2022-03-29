package com.momo.group.dto;

import com.momo.district.entity.City;
import com.momo.group.entity.Category;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupSearchConditionRequest {

    private String groupName;

    @Size(min = 1, message = "지역은 한 개 이상 선택해야 합니다.")
    private List<City> cities;

    @Size(min = 1, message = "모임 카테고리는 한 개 이상 선택해야 합니다.")
    private List<Category> categories;

    @NotNull(message = "페이지 번호는 필수값입니다.")
    private Integer page;

    @NotNull(message = "페이지 사이즈는 필수값입니다.")
    private Integer size;

    @Builder
    public GroupSearchConditionRequest(String groupName, List<City> cities, List<Category> categories, int page,
        int size) {
        this.groupName = groupName;
        this.cities = cities;
        this.categories = categories;
        this.page = page;
        this.size = size;
    }
}
