package com.momo.post.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.post.controller.dto.PostCardResponse;
import com.momo.post.controller.dto.PostResponse;
import com.momo.post.domain.model.PostType;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Optional<PostResponse> findByIdAndCountComment(Long postId);

    Page<PostCardResponse> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type, Pageable pageable);
}
