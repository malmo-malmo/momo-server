package com.momo.post.dto;

import com.momo.common.util.TimeFormatUtil;
import com.momo.post.entity.Comment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private Long id;
    private Long authorId;
    private String authorImage;
    private String authorNickname;
    private String contents;
    private String createdDate;

    @Builder
    public CommentResponse(Long id, Long authorId, String authorImage, String authorNickname, String contents,
        LocalDateTime createdDate) {
        this.id = id;
        this.authorId = authorId;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.contents = contents;
        this.createdDate = TimeFormatUtil.generateDateInfo(createdDate);
    }

    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
            .id(comment.getId())
            .authorId(comment.getUser().getId())
            .authorImage(comment.getUser().getImageUrl())
            .authorNickname(comment.getUser().getNickname())
            .contents(comment.getContents())
            .createdDate(comment.getCreatedDate())
            .build();
    }

    public static List<CommentResponse> listOf(List<Comment> comments) {
        return comments.stream()
            .map(CommentResponse::of)
            .collect(Collectors.toList());
    }
}
