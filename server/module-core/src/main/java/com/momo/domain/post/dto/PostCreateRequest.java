package com.momo.domain.post.dto;

import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
public class PostCreateRequest {

    @NotNull(message = "모임 ID는 필수값입니다.")
    private Long groupId;

    @NotBlank(message = "게시물 제목은 필수 입력값입니다.")
    private String title;

    @NotBlank(message = "게시물 내용은 필수 입력값입니다.")
    private String contents;

    @NotBlank(message = "게시물 타입은 필수 입력값입니다.")
    private String typeName;

    private List<MultipartFile> images;

    @Builder
    public PostCreateRequest(Long groupId, String title, String contents, String typeName,
        List<MultipartFile> images) {
        this.groupId = groupId;
        this.title = title;
        this.contents = contents;
        this.typeName = typeName;
        this.images = images;
    }

    public Post toEntity() {
        return Post.builder()
            .title(title)
            .contents(contents)
            .type(PostType.of(typeName))
            .build();
    }
}
