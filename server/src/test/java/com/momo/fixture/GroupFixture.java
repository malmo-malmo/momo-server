package com.momo.fixture;

import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.domain.model.Category;
import java.time.LocalDate;

public class GroupFixture {

    public static GroupCreateRequest GROUP_CREATE_REQUEST1 = GroupCreateRequest.builder()
        .groupName("운동 동아리")
        .category(Category.HEALTH.name())
        .startDate(LocalDate.of(2021, 11, 17))
        .recruitmentCnt(10L)
        .introduction("같이 운동하실분 모집합니다.")
        .groupImgUrl("이미지")
        .isOffline(true)
        .build();
}
