package com.momo.common.domain;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import java.util.Arrays;

public enum GroupCategory {
  HEALTH("건강"),
  EMPLOYMENT("취업"),
  SELF_DEVELOPMENT("자기계발"),
  HEALING("힐링"),
  ASSET("자산"),
  LIFE("생활"),
  HOBBY("취미"),
  RICE("밥약");

  private final String name;

  GroupCategory(String name) {
    this.name = name;
  }

  public String getCode() {
    return name();
  }

  public String getName() {
    return name;
  }

  public static GroupCategory of(String groupCategory) {
    return Arrays.stream(GroupCategory.values())
        .filter(category -> category.getCode().equals(groupCategory))
        .findFirst()
        .orElseThrow(() -> new CustomException(ErrorCode.GROUP_CATEGORY_NOT_FOUND));
  }
}
