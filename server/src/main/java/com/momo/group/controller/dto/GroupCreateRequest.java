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

    @NotBlank(message = "모임 이름은 필수 입력값입니다.")
    private String groupName;

    @NotNull(message = "모임 카테고리는 필수 입력값입니다.")
    private String category;

    private String university;

    private String location;

    @NotNull(message = "모임 시작일은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "인원수는 필수 입력값입니다.")
    private Long recruitmentCnt;

    @NotBlank(message = "모임 설명은 필수 입력값입니다.")
    private String introduction;

    @NotBlank(message = "모임 이미지는 필수 입력값입니다.")
    private String groupImgUrl;

    @NotNull(message = "모임 형식은 필수 입력값입니다.")
    private Boolean isOffline;

    @Builder
    public GroupCreateRequest(String groupName, String category, String university, String location,
        LocalDate startDate, Long recruitmentCnt, String introduction, String groupImgUrl, Boolean isOffline) {
        this.groupName = groupName;
        this.category = category;
        this.university = university;
        this.location = location;
        this.startDate = startDate;
        this.recruitmentCnt = recruitmentCnt;
        this.introduction = introduction;
        this.groupImgUrl = groupImgUrl;
        this.isOffline = isOffline;
    }

    public Groups toEntity() {
        return Groups.builder()
            .groupName(groupName)
            .category(Category.of(category))
            .university(university)
            .location(Location.of(location))
            .startDate(startDate)
            .recruitmentCnt(recruitmentCnt)
            .introduction(introduction)
            .isOffline(isOffline)
            .build();
    }
}
