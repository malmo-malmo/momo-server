package com.momo;

import static com.momo.common.FixtureComponents.CATEGORIES;
import static com.momo.common.FixtureComponents.CATEGORY;
import static com.momo.common.FixtureComponents.CITIES;
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
import static com.momo.common.FixtureComponents.PAGE;
import static com.momo.common.FixtureComponents.PAGE_SIZE;
import static com.momo.common.FixtureComponents.RECRUITMENT_CNT;
import static com.momo.common.LocationFixture.getLocation;

import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.request.GroupSearchConditionRequest;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.category.Category;
import com.momo.user.domain.User;

public class GroupFixture {

    public static Group getGroup(User user) {
        INCREASE_ID++;
        return Group.builder()
            .manager(user)
            .name(NAME + INCREASE_ID)
            .category(CATEGORY)
            .university(user.getUniversity())
            .location(getLocation())
            .imageUrl(IMAGE_URL)
            .introduction(INTRODUCTION + INCREASE_ID)
            .recruitmentCnt(RECRUITMENT_CNT)
            .isOffline(IS_OFFLINE)
            .isEnd(IS_END)
            .startDate(DATE)
            .build();
    }

    public static Group getGroup(User user, boolean isEnd) {
        INCREASE_ID++;
        return Group.builder()
            .manager(user)
            .name(NAME + INCREASE_ID)
            .category(CATEGORY)
            .university(user.getUniversity())
            .location(getLocation())
            .imageUrl(IMAGE_URL)
            .introduction(INTRODUCTION + INCREASE_ID)
            .recruitmentCnt(RECRUITMENT_CNT)
            .isOffline(IS_OFFLINE)
            .isEnd(isEnd)
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
            .university(user.getUniversity())
            .location(getLocation())
            .introduction(INTRODUCTION + INCREASE_ID)
            .recruitmentCnt(RECRUITMENT_CNT)
            .isOffline(IS_OFFLINE)
            .isEnd(IS_END)
            .startDate(DATE)
            .build();
    }

    public static GroupCreateRequest getGroupCreateRequest(Category category, String university) {
        INCREASE_ID++;
        return GroupCreateRequest.builder()
            .name(NAME + INCREASE_ID)
            .category(category)
            .city(CITY)
            .district(DISTRICT)
            .university(university)
            .isOffline(IS_OFFLINE)
            .startDate(DATE)
            .recruitmentCnt(RECRUITMENT_CNT)
            .introduction(INTRODUCTION + INCREASE_ID)
            .image(IMAGE)
            .build();
    }

    public static GroupCreateRequest getGroupCreateRequest(Category category, String university, String district) {
        INCREASE_ID++;
        return GroupCreateRequest.builder()
            .name(NAME + INCREASE_ID)
            .category(category)
            .city(CITY)
            .district(district)
            .university(university)
            .isOffline(IS_OFFLINE)
            .startDate(DATE)
            .recruitmentCnt(RECRUITMENT_CNT)
            .introduction(INTRODUCTION + INCREASE_ID)
            .image(IMAGE)
            .build();
    }

    public static GroupSearchConditionRequest getGroupSearchConditionRequest(String groupName) {
        return GroupSearchConditionRequest.builder()
            .groupName(groupName)
            .categories(CATEGORIES)
            .cities(CITIES)
            .page(PAGE)
            .size(PAGE_SIZE)
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

    public static GroupCreateResponse getGroupCreateResponse() {
        return GroupCreateResponse.builder()
            .id(1L)
            .imageUrl(IMAGE_URL)
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
