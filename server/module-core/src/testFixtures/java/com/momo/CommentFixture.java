package com.momo;

import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentsRequest;
import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import com.momo.domain.user.entity.User;

public class CommentFixture {

    private static final String CONTENTS = "내용";

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 10;

    private static Long INCREASE_ID = 0L;

    public static Comment getComment(Post post, User user) {
        INCREASE_ID++;
        return Comment.builder()
            .post(post)
            .user(user)
            .contents(CONTENTS + INCREASE_ID)
            .build();
    }

    public static Comment getCommentWithId(Post post, User user) {
        INCREASE_ID++;
        return Comment.builder()
            .id(INCREASE_ID)
            .post(post)
            .user(user)
            .contents(CONTENTS + INCREASE_ID)
            .build();
    }

    public static CommentCreateRequest getCommentCreateRequest(Long postId) {
        INCREASE_ID++;
        return CommentCreateRequest.builder()
            .postId(postId)
            .contents(CONTENTS + INCREASE_ID)
            .build();
    }

    public static CommentsRequest getCommentsRequest(Long postId) {
        return CommentsRequest.builder()
            .postId(postId)
            .page(PAGE)
            .size(PAGE_SIZE)
            .build();
    }
}
