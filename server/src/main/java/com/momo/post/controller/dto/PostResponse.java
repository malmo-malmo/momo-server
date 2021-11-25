package com.momo.post.controller.dto;

import com.momo.post.domain.model.Image;
import com.momo.post.domain.model.Post;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {

    private Long id;

    private String authorImage;

    private String authorNickname;

    private String title;

    private String contents;

    private List<String> imageUrls;

    @Builder
    public PostResponse(Long id, String authorImage, String authorNickname, String title, String contents,
        List<String> imageUrls) {
        this.id = id;
        this.authorImage = authorImage;
        this.authorNickname = authorNickname;
        this.title = title;
        this.contents = contents;
        this.imageUrls = imageUrls;
    }

    public static PostResponse of(Post post, List<Image> images) {
        return PostResponse.builder()
            .id(post.getId())
            .authorImage(post.getAuthor().getImage())
            .authorNickname(post.getAuthor().getNickname())
            .title(post.getTitle())
            .contents(post.getContents())
            .imageUrls(images.stream().map(Image::getImageUrl).collect(Collectors.toList()))
            .build();
    }
}
