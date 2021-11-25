package com.momo.post.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
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

    private Long commentCnt;

    @QueryProjection
    public PostCardResponse(Long id, String authorImage, String authorNickname, String title, String contents,
        Long commentCnt) {
        this.id = id;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.contents = contents;
        this.commentCnt = commentCnt;
    }
}
