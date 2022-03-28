package com.momo.domain.post.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.user.domain.User;
import java.util.List;

public interface PostRepositoryCustom {

    List<PostCardResponse> findAllWithCommentCntByGroupOrderByIdDesc(
        Group group, PostType type, Long lastPostId, int size
    );

    List<Post> findAllByGroupAndUserOrderByIdDesc(
        User user, PostType type, Long lastPostId, int size);
}
