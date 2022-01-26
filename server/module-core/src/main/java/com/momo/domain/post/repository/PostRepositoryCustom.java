package com.momo.domain.post.repository;

import com.momo.domain.group.entity.Group;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    List<PostCardResponse> findAllWithAuthorByGroupAndTypeOrderByCreatedDateDesc(Group group, PostType type,
        Pageable pageable);

    List<Post> findAllWithGroupAndAuthorByUserAndTypeOrderByCreatedDateDesc(User user, PostType type,
        Pageable pageable);
}
