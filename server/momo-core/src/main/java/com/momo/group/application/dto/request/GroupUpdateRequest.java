package com.momo.group.application.dto.request;

import com.momo.district.entity.City;
import com.momo.group.domain.category.Category;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupUpdateRequest {

    @NotNull(message = "모임 ID는 필수 입력값입니다.")
    private Long id;

    @NotNull(message = "모임 이름은 필수 입력값입니다.")
    private String name;

    @NotNull(message = "카테고리는 필수 입력값입니다.")
    private Category category;

    @NotNull(message = "학교 여부는 필수 입력값입니다.")
    private Boolean isUniversity;

    @NotNull(message = "지역은 필수 입력값입니다.")
    private City city;

    @NotBlank(message = "지역은 필수 입력값입니다.")
    private String district;

    @NotNull(message = "인원수는 필수 입력값입니다.")
    private int recruitmentCnt;

    @NotBlank(message = "설명은 필수 입력값입니다.")
    private String introduction;

    @NotNull(message = "온 오프라인 여부는 필수 입력값입니다.")
    private Boolean isOffline;

    @Builder
    public GroupUpdateRequest(
        Long id, String name, Category category, Boolean isUniversity,
        City city, String district, int recruitmentCnt, String introduction, Boolean isOffline
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isUniversity = isUniversity;
        this.city = city;
        this.district = district;
        this.recruitmentCnt = recruitmentCnt;
        this.introduction = introduction;
        this.isOffline = isOffline;
    }
}
