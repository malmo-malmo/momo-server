package com.momo.domain.post.repository;

import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> findAllByPostOrderByIdDesc(Post post, Long lastCommentId, int size);
}
