package com.momo.group.controller.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    @Size(min = 1)
    @NotNull(message = "관심 카테고리는 필수 입력값입니다.")
    private List<String> categories;
}
