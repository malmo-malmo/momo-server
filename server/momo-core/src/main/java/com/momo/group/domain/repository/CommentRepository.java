package com.momo.group.domain.repository;

import com.momo.group.domain.post.comment.Comment;
import com.momo.group.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    long countByPost(Post post);
}
