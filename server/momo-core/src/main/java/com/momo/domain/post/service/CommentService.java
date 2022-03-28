package com.momo.domain.post.service;

import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.dto.CommentsRequest;
import com.momo.domain.post.dto.CommentsResponse;
import com.momo.domain.user.domain.User;

public interface CommentService {

    CommentResponse createComment(User user, CommentCreateRequest request);

    CommentsResponse findPageByPostId(User user, CommentsRequest request);

    void deleteCommentById(Long commentId, User loginUser);
}
