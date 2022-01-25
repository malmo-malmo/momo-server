package com.momo.domain.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequest {

    @NotNull(message = "게시글 ID는 필수 입력 값입니다.")
    private Long postId;
    @NotBlank(message = "수정할 게시글 제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "수정할 게시글 내용은 필수 입력 값입니다.")
    private String content;

    @Builder
    public PostUpdateRequest(Long postId, String title, String content) {
        this.postId = postId;
        this.title = title;
        this.content = content;
    }
}
