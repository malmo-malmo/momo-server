package com.momo.post.domain.repository;

import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {

    List<PostImage> findAllByPost(Post post);
}
