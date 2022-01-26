package com.momo.domain.post.service;

import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.dto.CommentsRequest;
import com.momo.domain.post.dto.CommentsResponse;
import com.momo.domain.user.entity.User;

public interface CommentService {

    CommentResponse create(User user, CommentCreateRequest commentCreateRequest);

    CommentsResponse findPageByPostId(User user, CommentsRequest request);

    void deleteComment(Long commentId, User loginUser);
}
