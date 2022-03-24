package com.momo.domain.post.repository;

import static com.momo.domain.post.entity.QComment.comment;
import static com.momo.domain.user.entity.QUser.user;

import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
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
