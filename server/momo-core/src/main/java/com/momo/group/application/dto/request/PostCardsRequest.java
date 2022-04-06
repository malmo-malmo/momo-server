package com.momo.group.application.dto.request;

import com.momo.group.domain.post.PostType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCardsRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @NotNull(message = "게시물 타입은 필수 입력값입니다.")
    private PostType postType;

    private Long lastPostId;

    @NotNull(message = "페이지 사이즈는 필수값입니다.")
    private Integer size;

    @Builder
    public PostCardsRequest(Long groupId, PostType postType, Long lastPostId, Integer size) {
        this.groupId = groupId;
        this.postType = postType;
        this.lastPostId = lastPostId;
        this.size = size;
    }
}
