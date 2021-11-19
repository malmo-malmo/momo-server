package com.momo.group.controller.dto;

import com.momo.group.domain.model.Category;
import com.momo.group.domain.model.Groups;
import com.momo.user.domain.model.Location;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class GroupCreateRequest {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotNull(message = "카테고리는 필수 입력값입니다.")
    private String category;

    @NotNull(message = "학교 여부는 필수 입력값입니다.")
    private Boolean isUniversity;

    @NotBlank(message = "지역은 필수 입력값입니다.")
    private String location;

    @NotNull(message = "시작일은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "인원수는 필수 입력값입니다.")
    private int recruitmentCnt;

    @NotBlank(message = "설명은 필수 입력값입니다.")
    private String introduction;

    @NotBlank(message = "이미지는 필수 입력값입니다.")
    private String imageUrl;

    @NotNull(message = "온 오프라인 여부는 필수 입력값입니다.")
    private Boolean isOffline;

    @Builder
    public GroupCreateRequest(String name, String category, Boolean isUniversity, String location, LocalDate startDate,
        int recruitmentCnt, String introduction, String imageUrl, Boolean isOffline) {
        this.name = name;
        this.category = category;
        this.isUniversity = isUniversity;
        this.location = location;
        this.startDate = startDate;
        this.recruitmentCnt = recruitmentCnt;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.isOffline = isOffline;
    }

    public Groups toEntity() {
        return Groups.builder()
            .name(name)
            .category(Category.of(category))
            .location(Location.of(location))
            .startDate(startDate)
            .recruitmentCnt(recruitmentCnt)
            .introduction(introduction)
            .imageUrl(imageUrl)
            .isOffline(isOffline)
            .build();
    }
}
