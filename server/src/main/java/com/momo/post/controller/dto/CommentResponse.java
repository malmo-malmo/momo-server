package com.momo.post.controller.dto;

import com.momo.post.domain.model.Comment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String authorImage;

    private String authorNickname;

    private String contents;

    private LocalDateTime createdDate;

    @Builder
    public CommentResponse(Long id, String authorImage, String authorNickname, String contents,
        LocalDateTime createdDate) {
        this.id = id;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
            .id(comment.getId())
            .authorImage(comment.getUser().getImage())
            .authorNickname(comment.getUser().getNickname())
            .contents(comment.getContents())
            .createdDate(comment.getCreatedDate())
            .build();
    }

    public static List<CommentResponse> listOf(List<Comment> comments) {
        return comments.stream().map(CommentResponse::of).collect(Collectors.toList());
    }
}
