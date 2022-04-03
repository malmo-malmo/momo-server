package com.momo.common;

import static com.momo.district.entity.City.SEOUL;
import static com.momo.group.entity.Category.HEALTH;
import static com.momo.group.entity.Category.SELF_DEVELOPMENT;
import static com.momo.user.domain.model.SocialProvider.KAKAO;

import com.momo.district.entity.City;
import com.momo.group.entity.Category;
import com.momo.user.domain.model.SocialProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class FixtureComponents {

    public static Long INCREASE_ID = 0L;

    public static final int PAGE = 0;
    public static final int PAGE_SIZE = 10;
    public static final int RECRUITMENT_CNT = 10;

    public static final String NAME = "이름";
    public static final String TITLE = "제목";
    public static final String CONTENTS = "내용";
    public static final String NICKNAME = "닉네임";
    public static final String AUTHOR_NICKNAME = "닉네임";
    public static final String AUTHOR_IMAGE = "이미지";
    public static final String UNIVERSITY = "대학교";
    public static final String DISTRICT = "강동구";
    public static final String IMAGE_URL = "이미지 URL";
    public static final String INTRODUCTION = "소개";
    public static final String REFRESH_TOKEN = "리프레시 토큰";
    public static final String DEVICE_CODE = "기기 고유번호";

    public static final Boolean IS_OFFLINE = false;
    public static final boolean IS_END = false;

    public static final City CITY = SEOUL;
    public static final Category CATEGORY = HEALTH;
    public static final SocialProvider PROVIDER = KAKAO;
    public static final List<Category> CATEGORIES = List.of(HEALTH, SELF_DEVELOPMENT);
    public static final List<City> CITIES = List.of(SEOUL);

    public static final LocalDate DATE = LocalDate.of(2022, 1, 1);
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2022, 1, 1, 0, 0);

    public static MultipartFile IMAGE = new MockMultipartFile("image", "image".getBytes());
    public static List<MultipartFile> IMAGES = List.of(new MockMultipartFile("image", "image".getBytes()));
}
