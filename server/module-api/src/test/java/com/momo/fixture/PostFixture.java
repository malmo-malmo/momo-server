package com.momo.fixture;

import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.entity.PostType;

public class PostFixture {

    public static PostCreateRequest getPostCreateRequest(Long groupId) {
        return PostCreateRequest.builder()
            .groupId(groupId)
            .title("오늘 저녁 9시 풋살")
            .contents("오늘 저녁 9시부터 10시까지 풋살하실 분 10분 구해요!")
            .typeName(PostType.NORMAL.name())
            .build();
    }

    public static PostCreateRequest getNoticeCreateRequest(Long groupId) {
        return PostCreateRequest.builder()
            .groupId(groupId)
            .title("운동 모임 필독 사항 공지")
            .contents("1. 친목 금지....")
            .typeName(PostType.NOTICE.name())
            .build();
    }
}
