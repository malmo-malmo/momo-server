package com.momo;

import static com.momo.common.LocationFixture.getLocation;
import static com.momo.common.FixtureComponents.CATEGORY;
import static com.momo.common.FixtureComponents.CITY;
import static com.momo.common.FixtureComponents.DATE;
import static com.momo.common.FixtureComponents.DISTRICT;
import static com.momo.common.FixtureComponents.IMAGE;
import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.INTRODUCTION;
import static com.momo.common.FixtureComponents.IS_END;
import static com.momo.common.FixtureComponents.IS_OFFLINE;
import static com.momo.common.FixtureComponents.NAME;
import static com.momo.common.FixtureComponents.RECRUITMENT_CNT;

import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;

public class GroupFixture {

    public static Group getGroup(User user) {
        INCREASE_ID++;
        return Group.builder()
            .manager(user)
            .name(NAME + INCREASE_ID)
            .category(CATEGORY)
            .location(getLocation())
            .imageUrl(IMAGE_URL)
            .introduction(INTRODUCTION + INCREASE_ID)
            .recruitmentCnt(RECRUITMENT_CNT)
            .isOffline(IS_OFFLINE)
            .isEnd(IS_END)
            .startDate(DATE)
            .build();
    }

    public static Group getGroupWithId(User user) {
        INCREASE_ID++;
        return Group.builder()
            .id(INCREASE_ID)
            .manager(user)
            .name(NAME + INCREASE_ID)
            .category(CATEGORY)
            .imageUrl(IMAGE_URL)
            .location(getLocation())
            .introduction(INTRODUCTION + INCREASE_ID)
            .recruitmentCnt(RECRUITMENT_CNT)
            .isOffline(IS_OFFLINE)
            .isEnd(IS_END)
            .startDate(DATE)
            .build();
    }

    public static GroupCreateRequest getGroupCreateRequest(Category category, boolean isUniversity) {
        INCREASE_ID++;
        return GroupCreateRequest.builder()
            .name(NAME + INCREASE_ID)
            .category(category)
            .city(CITY)
            .district(DISTRICT)
            .isUniversity(isUniversity)
            .isOffline(IS_OFFLINE)
            .startDate(DATE)
            .recruitmentCnt(RECRUITMENT_CNT)
            .introduction(INTRODUCTION + INCREASE_ID)
            .image(IMAGE)
            .build();
    }

    public static GroupCreateRequest getGroupCreateRequest(Category category, boolean isUniversity, String district) {
        INCREASE_ID++;
        return GroupCreateRequest.builder()
            .name(NAME + INCREASE_ID)
            .category(category)
            .city(CITY)
            .district(district)
            .isUniversity(isUniversity)
            .isOffline(IS_OFFLINE)
            .startDate(DATE)
            .recruitmentCnt(RECRUITMENT_CNT)
            .introduction(INTRODUCTION + INCREASE_ID)
            .image(IMAGE)
            .build();
    }

    public static GroupResponse getGroupResponse() {
        return GroupResponse.builder()
            .id(1L)
            .managerId(1L)
            .name(NAME)
            .imageUrl(IMAGE_URL)
            .startDate(DATE)
            .location(getLocation())
            .isOffline(IS_OFFLINE)
            .introduction(INTRODUCTION)
            .recruitmentCnt(RECRUITMENT_CNT)
            .isEnd(IS_END)
            .participantCnt(1L)
            .isParticipant(false)
            .build();
    }

    public static GroupCardResponse getGroupCardResponse() {
        return GroupCardResponse.builder()
            .id(1L)
            .name(NAME)
            .imageUrl(IMAGE_URL)
            .startDate(DATE)
            .isOffline(IS_OFFLINE)
            .participantCnt(1L)
            .isFavoriteGroup(true)
            .build();
    }
}
