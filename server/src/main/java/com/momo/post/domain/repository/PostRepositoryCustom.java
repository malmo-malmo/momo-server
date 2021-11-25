package com.momo.post.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.post.controller.dto.PostCardResponse;
import com.momo.post.domain.model.PostType;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    List<PostCardResponse> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type, Pageable pageable);
}
