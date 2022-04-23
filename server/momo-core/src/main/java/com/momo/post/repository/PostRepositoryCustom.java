package com.momo.post.repository;

import com.momo.group.domain.Group;
import com.momo.post.dto.PostCardResponse;
import com.momo.post.entity.Post;
import com.momo.post.entity.PostType;
import com.momo.user.domain.User;
import java.util.List;

public interface PostRepositoryCustom {

    List<PostCardResponse> findAllWithCommentCntByGroupOrderByIdDesc(
        Group group, PostType type, Long lastPostId, int size
    );

    List<Post> findAllByGroupAndUserOrderByIdDesc(
        User user, PostType type, Long lastPostId, int size);
}
