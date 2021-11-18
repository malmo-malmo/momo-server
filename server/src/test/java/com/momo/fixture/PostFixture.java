package com.momo.fixture;

import com.momo.post.controller.dto.PostCreateRequest;

public class PostFixture {

    public static PostCreateRequest POST_CREATE_REQUEST1 = PostCreateRequest.builder()
        .title("오늘 저녁 9시 풋살")
        .contents("오늘 저녁 9시부터 10시까지 풋살하실 분 10분 구해요!")
        .build();
}
