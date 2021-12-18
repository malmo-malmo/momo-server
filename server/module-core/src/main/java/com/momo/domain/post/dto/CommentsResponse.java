package com.momo.domain.post.dto;

import java.util.List;

import com.momo.domain.post.domain.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsResponse {

    private List<CommentResponse> commentResponses;

    private Long commentCnt;

    public static CommentsResponse of(List<Comment> comments, Long commentCnt) {
        return new CommentsResponse(CommentResponse.listOf(comments), commentCnt);
    }
}
