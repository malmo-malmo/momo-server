package com.momo.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

  INTERNAL_SERVER_ERROR(500, "서버에 문제가 생겼습니다."),

  USER_ACCESS_DENIED(401, "해당 콘텐츠에 접근 권한이 없는 유저입니다."),
  UNAUTHORIZED_REDIRECT_URI(400, "인증되지 않은 REDIRECT_URI 입니다."),

  INVALID_INPUT_VALUE(400, "적절하지 않은 요청 값입니다."),
  INVALID_TYPE_VALUE(400, "요청 값의 타입이 잘못되었습니다."),
  METHOD_NOT_ALLOWED(405, "적절하지 않은 HTTP 메소드입니다."),

  INVALID_USER_ID(400, "잘못된 유저 ID 입니다."),

  GROUP_CATEGORY_NOT_FOUND(400, "해당 카테고리 타입은 존재하지 않습니다.");

  private final int status;
  private final String message;

  ErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
  }
}
