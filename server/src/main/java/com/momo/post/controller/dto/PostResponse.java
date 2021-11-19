package com.momo.post.controller.dto;

import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostImage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {

    private Long postId;

    private String userImage;

    private String nickname;

    private String title;

    private String contents;

    private int commentCnt;

    private List<String> postImages;


    @Builder
    public PostResponse(Long postId, String userImage, String nickname, String title, String contents, int commentCnt,
        List<String> postImages) {
        this.postId = postId;
        this.userImage = userImage;
        this.nickname = nickname;
        this.title = title;
        this.contents = contents;
        this.commentCnt = commentCnt;
        this.postImages = postImages;
    }

    public static PostResponse of(Post post, List<PostImage> postImages) {
        return PostResponse.builder()
            .postId(post.getId())
            .userImage(post.getUser().getImage())
            .nickname(post.getUser().getNickname())
            .title(post.getTitle())
            .contents(post.getContents())
            .commentCnt(post.getCommentCnt())
            .postImages(postImages.stream().map(PostImage::getImageUrl).collect(Collectors.toList()))
            .build();
    }
}
