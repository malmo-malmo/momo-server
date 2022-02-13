package com.momo.domain.group.dto;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.Location;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class GroupCreateRequest {

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotNull(message = "카테고리는 필수 입력값입니다.")
    private Category category;

    @NotNull(message = "학교 여부는 필수 입력값입니다.")
    private Boolean isUniversity;

    @NotNull(message = "지역은 필수 입력값입니다.")
    private City city;

    @NotBlank(message = "지역은 필수 입력값입니다.")
    private String district;

    @NotNull(message = "시작일은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "인원수는 필수 입력값입니다.")
    private int recruitmentCnt;

    @NotBlank(message = "설명은 필수 입력값입니다.")
    private String introduction;

    @NotNull(message = "이미지는 필수입니다.")
    private MultipartFile image;

    @NotNull(message = "온 오프라인 여부는 필수 입력값입니다.")
    private Boolean isOffline;

    @Builder
    public GroupCreateRequest(String name, Category category, Boolean isUniversity, City city, String district,
        LocalDate startDate, int recruitmentCnt, String introduction, MultipartFile image, Boolean isOffline) {
        this.name = name;
        this.category = category;
        this.isUniversity = isUniversity;
        this.city = city;
        this.district = district;
        this.startDate = startDate;
        this.recruitmentCnt = recruitmentCnt;
        this.introduction = introduction;
        this.image = image;
        this.isOffline = isOffline;
    }

    public Group toEntity() {
        return Group.builder()
            .name(name)
            .category(category)
            .location(Location.fromEmptyUniversity(city, district))
            .startDate(startDate)
            .recruitmentCnt(recruitmentCnt)
            .introduction(introduction)
            .isOffline(isOffline)
            .build();
    }
}
