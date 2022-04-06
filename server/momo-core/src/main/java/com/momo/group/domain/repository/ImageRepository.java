package com.momo.group.domain.repository;

import com.momo.group.domain.post.image.Image;
import com.momo.group.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByPost(Post post);
}
