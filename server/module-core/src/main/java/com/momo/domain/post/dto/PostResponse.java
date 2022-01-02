package com.momo.domain.post.dto;

import com.momo.domain.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {

    private Long id;

    private Long authorId;

    private String authorImage;

    private String authorNickname;

    private String title;

    private String contents;

    private List<String> imageUrls;

    private LocalDateTime createdDate;

    @Builder
    public PostResponse(Long id, Long authorId, String authorImage, String authorNickname, String title,
        String contents, List<String> imageUrls, LocalDateTime createdDate) {
        this.id = id;
        this.authorId = authorId;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.contents = contents;
        this.imageUrls = imageUrls;
        this.createdDate = createdDate;
    }

    public static PostResponse of(Post post) {
        return PostResponse.builder()
            .id(post.getId())
            .authorId(post.getAuthor().getId())
            .authorImage(post.getAuthor().getImageUrl())
            .authorNickname(post.getAuthor().getNickname())
            .title(post.getTitle())
            .contents(post.getContents())
            .imageUrls(post.getImages().toImageUrls())
            .createdDate(post.getCreatedDate())
            .build();
    }
}
