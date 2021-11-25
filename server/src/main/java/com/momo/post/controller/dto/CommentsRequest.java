package com.momo.post.controller.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsRequest {

    @NotNull(message = "게시물 ID는 필수값입니다.")
    private Long postId;

    @NotNull(message = "페이지 번호는 필수값입니다.")
    private Integer page;

    @NotNull(message = "페이지 사이즈는 필수값입니다.")
    private Integer size;
}
