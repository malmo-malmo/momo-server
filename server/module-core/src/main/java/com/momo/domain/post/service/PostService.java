package com.momo.domain.post.service;

import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.PostCardsRequest;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostUpdateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.user.entity.User;
import java.io.IOException;
import java.util.List;

public interface PostService {

    PostResponse create(User user, PostCreateRequest request) throws IOException;

    PostResponse findById(User user, Long postId);

    List<PostCardResponse> findPageByGroupIdAndType(User user, PostCardsRequest request);

    void updatePost(PostUpdateRequest request, User user);

    void deletePost(Long postId, User user);
}
