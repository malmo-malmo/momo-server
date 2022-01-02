package com.momo.domain.post.repository;

import com.momo.domain.post.entity.Image;
import com.momo.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByPost(Post post);
}
