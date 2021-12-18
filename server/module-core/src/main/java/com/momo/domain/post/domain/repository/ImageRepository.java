package com.momo.domain.post.domain.repository;

import com.momo.domain.post.domain.model.Image;
import com.momo.domain.post.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByPost(Post post);
}
