package com.momo.domain.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentCreateRequest {

    @NotNull(message = "게시물 ID는 필수값입니다.")
    private Long postId;

    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    private String contents;

    @Builder
    public CommentCreateRequest(Long postId, String contents) {
        this.postId = postId;
        this.contents = contents;
    }
}
