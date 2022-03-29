package com.momo.post.service;

import com.momo.post.dto.CommentCreateRequest;
import com.momo.post.dto.CommentResponse;
import com.momo.post.dto.CommentsRequest;
import com.momo.post.dto.CommentsResponse;
import com.momo.user.entity.User;

public interface CommentService {

    CommentResponse createComment(User user, CommentCreateRequest request);

    CommentsResponse findPageByPostId(User user, CommentsRequest request);

    void deleteCommentById(Long commentId, User loginUser);
}
