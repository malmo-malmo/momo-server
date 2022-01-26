package com.momo.domain.post.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PostUpdateRequest {

    @NotNull(message = "게시글 ID는 필수 입력 값입니다.")
    private Long postId;
    @NotBlank(message = "수정할 게시글 제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "수정할 게시글 내용은 필수 입력 값입니다.")
    private String content;

    private List<MultipartFile> images;

    @Builder
    public PostUpdateRequest(Long postId, String title, String content,
        List<MultipartFile> images) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.images = images;
    }
}
