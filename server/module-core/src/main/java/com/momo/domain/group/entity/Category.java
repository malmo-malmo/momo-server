package com.momo.domain.group.entity;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Category {
    HEALTH("건강"),
    RICE("밥약"),
    SELF_DEVELOPMENT("자기관리"),
    LIFE("생활"),
    HOBBY("취미"),
    STOCK("주식"),
    HEALING("힐링"),
    EMPLOYMENT("취업");

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

    public static Category of(String category) {
        return Arrays.stream(Category.values())
            .filter(v -> v.getCode().equals(category))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.GROUP_CATEGORY_NOT_FOUND));
    }

    public static List<Category> listOf(List<String> categories) {
        if (Objects.isNull(categories)) {
            return null;
        }
        return categories.stream()
            .map(Category::of)
            .collect(Collectors.toList());
    }
}
