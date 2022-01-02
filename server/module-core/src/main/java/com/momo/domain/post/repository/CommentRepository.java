package com.momo.domain.post.repository;

import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPostOrderByCreatedDateAsc(Post post, Pageable pageable);
}
