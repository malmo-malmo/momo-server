package com.momo.domain.post.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCardsRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @NotBlank(message = "게시물 타입은 필수 입력값입니다.")
    private String type;

    @NotNull(message = "페이지 번호는 필수값입니다.")
    private Integer page;

    @NotNull(message = "페이지 사이즈는 필수값입니다.")
    private Integer size;
}
