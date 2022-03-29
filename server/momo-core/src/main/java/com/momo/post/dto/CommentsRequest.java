package com.momo.post.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsRequest {

    @NotNull(message = "게시물 ID는 필수값입니다.")
    private Long postId;

    private Long lastCommentId;

    @NotNull(message = "페이지 사이즈는 필수값입니다.")
    private Integer size;

    @Builder
    public CommentsRequest(Long postId, Long lastCommentId, Integer size) {
        this.postId = postId;
        this.lastCommentId = lastCommentId;
        this.size = size;
    }
}
