package com.momo.domain.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequest {

    @NotNull(message = "게시물 ID는 필수값입니다.")
    private Long postId;

    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    private String contents;
}
