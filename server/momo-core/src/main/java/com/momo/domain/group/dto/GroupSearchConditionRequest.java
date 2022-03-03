package com.momo.domain.group.dto;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupSearchConditionRequest {

    @NotNull(message = "모임 이름(검색어)은 필수값입니다.")
    private String groupName;

    private List<City> cities;

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
