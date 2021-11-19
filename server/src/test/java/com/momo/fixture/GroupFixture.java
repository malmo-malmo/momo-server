package com.momo.fixture;

import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.domain.model.Category;
import com.momo.user.domain.model.Location;
import java.time.LocalDate;

public class GroupFixture {

    public static GroupCreateRequest GROUP_CREATE_REQUEST1 = GroupCreateRequest.builder()
        .name("축구 동아리")
        .category(Category.HEALTH.name())
        .location(Location.GANGDONG_GU.name())
        .isUniversity(true)
        .startDate(LocalDate.of(2021, 11, 17))
        .recruitmentCnt(10)
        .introduction("같이 축구하실 분 모집합니다.")
        .imageUrl("이미지")
        .isOffline(true)
        .build();

    public static GroupCreateRequest GROUP_CREATE_REQUEST2 = GroupCreateRequest.builder()
        .name("모각코 동아리")
        .category(Category.SELF_DEVELOPMENT.name())
        .location(Location.GANGDONG_GU.name())
        .isUniversity(true)
        .startDate(LocalDate.of(2021, 9, 17))
        .recruitmentCnt(10)
        .introduction("캠키고 같이 코딩하실 분 모집합니다.")
        .imageUrl("이미지")
        .isOffline(false)
        .build();

    public static GroupCreateRequest GROUP_CREATE_REQUEST3 = GroupCreateRequest.builder()
        .name("농구 동아리")
        .category(Category.HEALTH.name())
        .location(Location.GANGNAM_GU.name())
        .isUniversity(false)
        .startDate(LocalDate.of(2021, 3, 17))
        .recruitmentCnt(10)
        .introduction("같이 농구하실 분 모집합니다.")
        .imageUrl("이미지")
        .isOffline(true)
        .build();
}
