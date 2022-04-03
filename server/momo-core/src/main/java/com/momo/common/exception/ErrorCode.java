package com.momo.common.exception;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "서버에 문제가 생겼습니다."),
    CAREER_NET_SERVER_ERROR(500, "커리어넷 서버에 문제가 생겼습니다"),
    PARSING_DISTRICT_FILE_ERROR(500, "행정구역 파일을 읽는 과정에서 문제가 생겼습니다"),
    CITY_CATEGORY_NAME_NOT_FOUND(500, "지역(시/도) 카테고리 타입이 존재하지 않습니다."),
    IMAGE_FILE_UPLOAD_ERROR(500, "이미지 파일을 업로드하는 과정에서 문제가 생겼습니다."),

    INVALID_OAUTH_AUTHORIZATION_CODE(401, "유효하지 않은 OAuth 인가 코드 입니다."),
    INVALID_OAUTH_ACCESS_TOKEN(401, "유효하지 않은 OAuth 엑세스 토큰입니다."),
    INVALID_ACCESS_TOKEN(401, "유효하지 않은 엑세스 토큰입니다."),
    INVALID_REFRESH_TOKEN(401, "유효하지 않은 리프레쉬 토큰입니다."),
    INVALID_DEVICE_CODE(401, "등록된 기기 고유번호와 다릅니다."),
    TOKEN_NOT_FOUND_USER(401, "토큰에서 유저 정보를 찾을 수 없습니다."),
    SOCIAL_LOGIN_NOT_SUPPORT(400, "해당 소셜 로그인은 지원하지 않습니다"),

    USER_ACCESS_DENIED(401, "해당 콘텐츠에 접근 권한이 없는 유저입니다."),

    INVALID_INPUT_VALUE(400, "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "요청 값의 타입이 잘못되었습니다."),
    METHOD_NOT_ALLOWED(405, "적절하지 않은 HTTP 메소드입니다."),
    INVALID_INDEX_NUMBER(400, "잘못된 인덱스 번호입니다."),

    DUPLICATED_NICKNAME(400, "이미 존재하는 닉네임입니다."),
    INVALID_FAVORITE_CATEGORY(400, "최소 한 개의 관심 카테고리를 선택해야 합니다."),

    CITY_CATEGORY_CODE_NOT_FOUND(400, "지역(시/도) 카테고리 타입이 존재하지 않습니다."),

    GROUP_CATEGORY_NOT_FOUND(400, "해당 모임 카테고리 타입은 존재하지 않습니다."),
    GROUP_MANAGER_WITHDRAW_NOT_ALLOW(400, "해당 모임의 관리자는 탈퇴할 수 없습니다."),
    GROUP_MANAGER_AUTHORIZED(401, "모임 관리자에게만 허용된 기능입니다."),
    GROUP_PARTICIPANT_AUTHORIZED(401, "모임 참여자에게만 허용된 기능입니다."),
    GROUP_PARTICIPANT_UNAUTHORIZED(401, "해당 모임의 참여자가 아닙니다."),
    POST_CONTROL_UNAUTHORIZED(401, "해당 게시글에 접근 권한이 없습니다."),
    GROUP_OVER_CAPACITY(400, "해당 모임 정원이 초과되었습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorCode fromMessage(String message) {
        return Arrays.stream(ErrorCode.values())
            .filter(errorCode -> errorCode.getMessage().equals(message))
            .findFirst()
            .orElse(null);
    }
}
