package com.momo.group.domain.model;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

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

    public static Category of(String category) {
        return Arrays.stream(Category.values())
            .filter(v -> v.getCode().equals(category))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.GROUP_CATEGORY_NOT_FOUND));
    }

    public static List<Category> listOf(List<String> categories) {
        return categories.stream().map(Category::of).collect(Collectors.toList());
    }

    public static String toEntitySaveFormat(List<String> categories) {
        return StringUtils.join(Category.listOf(categories), ",");
    }
}
