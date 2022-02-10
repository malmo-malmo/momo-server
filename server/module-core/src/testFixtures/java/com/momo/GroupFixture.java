package com.momo;

import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class GroupFixture {

    private static final String NAME = "이름";
    private static final String DISTRICT = "강동구";
    private static final String UNIVERSITY = "대학교";
    private static final String INTRODUCTION = "소개";
    private static final String IMAGE_URL = "이미지 URL";

    private static final Category CATEGORY = Category.HEALTH;
    private static final City CITY = City.SEOUL;

    private static final boolean IS_OFFLINE = true;
    private static final boolean IS_END = false;

    private static final LocalDate DATE = LocalDate.of(2022, 1, 1);

    public static final MultipartFile IMAGE_FILE = new MockMultipartFile("image", "image".getBytes());

    private static final int RECRUITMENT_CNT = 10;
    private static Long INCREASE_ID = 0L;

    public static Group getGroup(User user) {
        INCREASE_ID++;
        return Group.builder()
            .manager(user)
            .name(NAME + INCREASE_ID)
            .category(CATEGORY)
            .city(CITY)
            .district(DISTRICT)
            .imageUrl(IMAGE_URL)
            .introduction(INTRODUCTION + INCREASE_ID)
            .university(UNIVERSITY + INCREASE_ID)
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
            .city(CITY)
            .district(DISTRICT)
            .imageUrl(IMAGE_URL)
            .introduction(INTRODUCTION + INCREASE_ID)
            .university(UNIVERSITY + INCREASE_ID)
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
            .image(IMAGE_FILE)
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
            .image(IMAGE_FILE)
            .build();
    }

    public static GroupResponse getGroupResponse() {
        return GroupResponse.builder()
            .id(1L)
            .managerId(1L)
            .name(NAME)
            .imageUrl(IMAGE_URL)
            .startDate(DATE)
            .university(UNIVERSITY)
            .city(CITY)
            .district(DISTRICT)
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
