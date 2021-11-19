package com.momo.post.domain.repository;

import com.momo.group.domain.model.Groups;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type, Pageable pageable);
}
