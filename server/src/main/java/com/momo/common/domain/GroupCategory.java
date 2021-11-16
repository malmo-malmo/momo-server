package com.momo.common.domain;

public enum GroupCategory {
  HEALTH("건강"),
  GAME("게임"),
  STUDY("공부"),
  SCHOOL("학교");

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
}
