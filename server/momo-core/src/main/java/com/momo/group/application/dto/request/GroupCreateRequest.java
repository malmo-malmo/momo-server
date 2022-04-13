package com.momo.group.application.dto.request;

import com.momo.district.entity.City;
import com.momo.group.domain.category.Category;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
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

    private String university;

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
    public GroupCreateRequest(String name, Category category, String university, City city, String district,
        LocalDate startDate, int recruitmentCnt, String introduction, MultipartFile image, Boolean isOffline) {
        this.name = name;
        this.category = category;
        this.university = university;
        this.city = city;
        this.district = district;
        this.startDate = startDate;
        this.recruitmentCnt = recruitmentCnt;
        this.introduction = introduction;
        this.image = image;
        this.isOffline = isOffline;
    }
}
