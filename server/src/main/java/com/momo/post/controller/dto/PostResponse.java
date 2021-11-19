package com.momo.post.controller.dto;

import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostImage;
import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {

    private Long id;

    private String authorImage;

    private String authorNickname;

    private String title;

    private String contents;

    private int commentCnt;

    private List<String> postImages;

    @QueryProjection
    @Builder
    public PostResponse(Long id, String authorImage, String authorNickname, String title, String contents,
        int commentCnt, List<String> postImages) {
        this.id = id;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.contents = contents;
        this.commentCnt = commentCnt;
        this.postImages = postImages;
    }

    public static PostResponse of(Post post, List<PostImage> postImages) {
        return PostResponse.builder()
            .id(post.getId())
            .authorImage(post.getAuthor().getImage())
            .authorNickname(post.getAuthor().getNickname())
            .title(post.getTitle())
            .contents(post.getContents())
            .commentCnt(post.getCommentCnt())
            .postImages(postImages.stream().map(PostImage::getImageUrl).collect(Collectors.toList()))
            .build();
    }

}
