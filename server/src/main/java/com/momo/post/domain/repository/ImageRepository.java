package com.momo.post.domain.repository;

import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByPost(Post post);
}
