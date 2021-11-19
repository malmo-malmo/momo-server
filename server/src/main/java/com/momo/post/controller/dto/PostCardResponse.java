package com.momo.post.controller.dto;

import com.momo.post.domain.model.Post;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCardResponse {

    private Long id;

    private String authorImage;

    private String authorNickname;

    private String title;

    private String contents;

    private int commentCnt;

    @Builder
    public PostCardResponse(Long id, String authorImage, String authorNickname, String title, String contents,
        int commentCnt) {
        this.id = id;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.contents = contents;
        this.commentCnt = commentCnt;
    }

    public static PostCardResponse of(Post post) {
        return PostCardResponse.builder()
            .id(post.getId())
            .authorImage(post.getAuthor().getImage())
            .authorNickname(post.getAuthor().getNickname())
            .title(post.getTitle())
            .contents(post.getContents())
            .commentCnt(post.getCommentCnt())
            .build();

    }

    public static List<PostCardResponse> listOf(List<Post> posts) {
        return posts.stream().map(PostCardResponse::of).collect(Collectors.toList());
    }
}
