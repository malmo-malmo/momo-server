package com.momo.group.domain.model;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Category {
    HEALTH("건강"),
    EMPLOYMENT("취업"),
    SELF_DEVELOPMENT("자기계발"),
    HEALING("힐링"),
    ASSET("자산"),
    LIFE("생활"),
    HOBBY("취미"),
    RICE("밥약");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getCode() {
        return name();
    }

    public String getName() {
        return name;
    }

    private static Category of(String groupCategory) {
        return Arrays.stream(Category.values())
            .filter(v -> v.getCode().equals(groupCategory))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.GROUP_CATEGORY_NOT_FOUND));
    }

    private static List<Category> listOf(List<String> groupCategories) {
        if (Objects.isNull(groupCategories) || groupCategories.size() == 0) {
            throw new CustomException(ErrorCode.INVALID_GROUP_CATEGORY_LENGTH);
        }
        return groupCategories.stream().map(Category::of).collect(Collectors.toList());
    }

    public static List<Category> fromStrings(List<String> categories) {
        return Category.listOf(categories);
    }
}
