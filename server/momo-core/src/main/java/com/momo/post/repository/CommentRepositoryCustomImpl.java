package com.momo.post.repository;

import static com.momo.post.entity.QComment.comment;
import static com.momo.user.domain.QUser.user;

import com.momo.post.entity.Comment;
import com.momo.post.entity.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findAllByPostOrderByIdDesc(Post post, Long lastCommentId, int size) {
        return queryFactory
            .selectFrom(comment)
            .leftJoin(comment.user, user).fetchJoin()
            .where(
                comment.post.eq(post),
                ltLastCommentId(lastCommentId)
            )
            .orderBy(comment.id.desc())
            .limit(size)
            .fetch();
    }

    private BooleanExpression ltLastCommentId(Long commentId) {
        if (Objects.isNull(commentId)) {
            return null;
        }
        return comment.id.lt(commentId);
    }
}
