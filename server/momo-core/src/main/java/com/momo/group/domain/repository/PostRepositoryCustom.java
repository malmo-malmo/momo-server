package com.momo.group.domain.repository;

import com.momo.group.domain.Group;
import com.momo.group.application.dto.response.PostCardResponse;
import com.momo.group.domain.post.Post;
import com.momo.group.domain.post.PostType;
import com.momo.user.domain.User;
import java.util.List;

public interface PostRepositoryCustom {

    List<PostCardResponse> findAllWithCommentCntByGroupOrderByIdDesc(
        Group group, PostType type, Long lastPostId, int size
    );

    List<Post> findAllByGroupAndUserOrderByIdDesc(
        User user, PostType type, Long lastPostId, int size);
}
