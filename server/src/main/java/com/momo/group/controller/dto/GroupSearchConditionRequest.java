package com.momo.group.controller.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupSearchConditionRequest {

    private List<String> cities;

    private List<String> categories;

    @NotNull(message = "페이지 번호는 필수값입니다.")
    private Integer page;

    @NotNull(message = "페이지 사이즈는 필수값입니다.")
    private Integer size;

    @Builder
    public GroupSearchConditionRequest(List<String> cities, List<String> categories, int page, int size) {
        this.cities = cities;
        this.categories = categories;
        this.page = page;
        this.size = size;
    }
}
