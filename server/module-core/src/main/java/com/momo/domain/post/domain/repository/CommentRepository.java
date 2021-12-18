package com.momo.domain.post.domain.repository;

import com.momo.domain.post.domain.model.Comment;
import com.momo.domain.post.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPostOrderByCreatedDateAsc(Post post, Pageable pageable);
}
