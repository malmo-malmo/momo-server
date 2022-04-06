package com.momo.group.domain.repository;

import com.momo.group.domain.post.comment.Comment;
import com.momo.group.domain.post.Post;
import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> findAllByPostOrderByIdDesc(Post post, Long lastCommentId, int size);
}
