package com.momo.post.repository;

import com.momo.post.entity.Comment;
import com.momo.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    long countByPost(Post post);
}
