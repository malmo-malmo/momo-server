package com.momo.post.controller.dto;

import com.momo.post.domain.model.Image;
import com.momo.post.domain.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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

    public static PostResponse of(Post post, List<Image> images) {
        return PostResponse.builder()
            .id(post.getId())
            .authorId(post.getAuthor().getId())
            .authorImage(post.getAuthor().getImageUrl())
            .authorNickname(post.getAuthor().getNickname())
            .title(post.getTitle())
            .contents(post.getContents())
            .imageUrls(images.stream().map(Image::getImageUrl).collect(Collectors.toList()))
            .createdDate(post.getCreatedDate())
            .build();
    }
}
