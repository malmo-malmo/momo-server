package com.momo.domain.post.domain.repository;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.post.domain.model.PostType;
import com.momo.domain.post.dto.PostCardResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostCardResponse> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type, Pageable pageable);
}
