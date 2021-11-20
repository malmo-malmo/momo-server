package com.momo.group.controller.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupSearchConditionRequest {

    private List<String> cities;

    private List<String> categories;

    private int page;

    private int size;

    @Builder
    public GroupSearchConditionRequest(List<String> cities, List<String> categories, int page, int size) {
        this.cities = cities;
        this.categories = categories;
        this.page = page;
        this.size = size;
    }
}
