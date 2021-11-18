package com.momo.post.domain.model;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import java.util.Arrays;

public enum PostType {

    NORMAL("일반"), NOTICE("공지사항");

    private final String name;

    PostType(String name) {
        this.name = name;
    }

    public String getCode() {
        return name();
    }

    public String getName() {
        return name;
    }

    public static PostType of(String type) {
        return Arrays.stream(PostType.values())
            .filter(v -> v.getCode().equals(type))
            .findFirst()
            .orElseThrow(() -> new CustomException(ErrorCode.GROUP_CATEGORY_NOT_FOUND));
    }
}
