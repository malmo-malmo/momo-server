package com.momo.domain.post.repository;

import com.momo.domain.group.entity.Groups;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.dto.PostCardResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostCardResponse> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type, Pageable pageable);
}
