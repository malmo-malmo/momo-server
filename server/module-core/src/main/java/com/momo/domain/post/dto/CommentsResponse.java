package com.momo.domain.post.dto;

import com.momo.domain.post.entity.Comment;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsResponse {

    private List<CommentResponse> commentResponses;

    private Long commentCnt;

    @Builder
    public CommentsResponse(List<CommentResponse> commentResponses, Long commentCnt) {
        this.commentResponses = commentResponses;
        this.commentCnt = commentCnt;
    }

    public static CommentsResponse of(List<Comment> comments, Long commentCnt) {
        return new CommentsResponse(CommentResponse.listOf(comments), commentCnt);
    }
}
