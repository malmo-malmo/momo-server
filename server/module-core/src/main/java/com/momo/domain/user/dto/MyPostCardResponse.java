package com.momo.domain.user.dto;

import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.entity.Post;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyPostCardResponse {

    private String groupName;
    private PostCardResponse postCardResponse;

    @Builder
    public MyPostCardResponse(String groupName, PostCardResponse postCardResponse) {
        this.groupName = groupName;
        this.postCardResponse = postCardResponse;
    }

    private static MyPostCardResponse of(Post post) {
        return MyPostCardResponse.builder()
            .groupName(post.getGroup().getName())
            .postCardResponse(PostCardResponse.of(post))
            .build();
    }

    public static List<MyPostCardResponse> listOf(List<Post> posts) {
        return posts.stream()
            .map(MyPostCardResponse::of)
            .collect(Collectors.toList());
    }
}
