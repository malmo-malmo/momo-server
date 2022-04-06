package com.momo.group.application.dto.response;

import com.momo.group.domain.post.Post;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
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

    private LocalDateTime createdDate;

    private Long commentCnt;

    @Builder
    @QueryProjection
    public PostCardResponse(Long id, String authorImage, String authorNickname, String title, String contents,
        LocalDateTime createdDate, Long commentCnt) {
        this.id = id;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.commentCnt = commentCnt;
    }

    public static PostCardResponse of(Post post) {
        return PostCardResponse.builder()
            .id(post.getId())
            .authorImage(post.getAuthor().getImageUrl())
            .authorNickname(post.getAuthor().getNickname())
            .title(post.getTitle())
            .contents(post.getContents())
            .createdDate(post.getCreatedDate())
            .build();
    }
}
