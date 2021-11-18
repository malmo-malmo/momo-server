package com.momo.post.controller.dto;

import com.momo.post.domain.model.Post;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCreateRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @NotBlank(message = "게시글 제목은 필수 입력값입니다.")
    private String title;

    @NotBlank(message = "게시글 내용은 필수 입력값입니다.")
    private String contents;

    private List<String> imageUrls;

    @Builder
    public PostCreateRequest(Long groupId, String title, String contents, List<String> imageUrls) {
        this.groupId = groupId;
        this.title = title;
        this.contents = contents;
        this.imageUrls = imageUrls;
    }

    public Post toEntity() {
        return Post.builder()
            .title(title)
            .contents(contents)
            .build();
    }
}
