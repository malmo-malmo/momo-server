package com.momo.post.repository;

import com.momo.post.entity.Comment;
import com.momo.post.entity.Post;
import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> findAllByPostOrderByIdDesc(Post post, Long lastCommentId, int size);
}
