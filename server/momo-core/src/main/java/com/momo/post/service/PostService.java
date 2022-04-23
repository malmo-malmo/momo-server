package com.momo.post.service;

import com.momo.post.dto.PostCardResponse;
import com.momo.post.dto.PostCardsRequest;
import com.momo.post.dto.PostCreateRequest;
import com.momo.post.dto.PostResponse;
import com.momo.post.dto.PostUpdateRequest;
import com.momo.user.domain.User;
import java.util.List;

public interface PostService {

    PostResponse create(User user, PostCreateRequest request);

    PostResponse findById(User user, Long postId);

    List<PostCardResponse> findPageByGroupId(User user, PostCardsRequest request);

    void updatePost(PostUpdateRequest request, User user);

    void deletePost(Long postId, User user);
}
