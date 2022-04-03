package com.momo.post.repository;

import com.momo.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @Query("select p from Post p join fetch p.author where p.id = ?1")
    Post findPostAndAuthorById(Long id);
}
