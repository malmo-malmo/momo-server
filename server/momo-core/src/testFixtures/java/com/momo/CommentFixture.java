package com.momo;

import static com.momo.common.FixtureComponents.AUTHOR_IMAGE;
import static com.momo.common.FixtureComponents.AUTHOR_NICKNAME;
import static com.momo.common.FixtureComponents.CONTENTS;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.PAGE_SIZE;

import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.dto.CommentsRequest;
import com.momo.domain.post.dto.CommentsResponse;
import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import com.momo.domain.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;

public class CommentFixture {

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
            .size(PAGE_SIZE)
            .build();
    }

    public static CommentResponse getCommentResponse() {
        return CommentResponse.builder()
            .id(1L)
            .authorId(1L)
            .authorImage(AUTHOR_IMAGE)
            .authorNickname(AUTHOR_NICKNAME)
            .contents(CONTENTS)
            .createdDate(LocalDateTime.now())
            .build();
    }

    public static CommentsResponse getCommentsResponse(List<CommentResponse> commentResponses) {
        return CommentsResponse.builder()
            .commentCnt(1L)
            .commentResponses(commentResponses)
            .build();
    }
}
