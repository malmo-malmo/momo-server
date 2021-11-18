package com.momo.fixture;

import com.momo.post.controller.dto.PostCreateRequest;
import com.momo.post.domain.model.PostType;
import java.util.List;

public class PostFixture {

    public static PostCreateRequest POST_CREATE_REQUEST1 = PostCreateRequest.builder()
        .title("오늘 저녁 9시 풋살")
        .contents("오늘 저녁 9시부터 10시까지 풋살하실 분 10분 구해요!")
        .postType(PostType.NORMAL.name())
        .imageUrls(List.of("이미지1", "이미지2"))
        .build();

    public static PostCreateRequest NOTICE_CREATE_REQUEST1 = PostCreateRequest.builder()
        .title("운동 모임 필독 사항 공지")
        .contents("1. 친목 금지....")
        .postType(PostType.NOTICE.name())
        .build();
}
