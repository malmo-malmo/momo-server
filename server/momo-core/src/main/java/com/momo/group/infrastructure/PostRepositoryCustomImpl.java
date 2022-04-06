package com.momo.group.infrastructure;

import static com.momo.group.entity.QGroup.group;
import static com.momo.post.entity.QComment.comment;
import static com.momo.post.entity.QPost.post;
import static com.momo.user.domain.model.QUser.user;

import com.momo.group.domain.Group;
import com.momo.group.application.dto.response.PostCardResponse;
import com.momo.group.domain.repository.PostRepositoryCustom;
import com.momo.post.dto.QPostCardResponse;
import com.momo.group.domain.post.Post;
import com.momo.group.domain.post.PostType;
import com.momo.user.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostCardResponse> findAllWithCommentCntByGroupOrderByIdDesc(
        Group group, PostType type, Long lastPostId, int size
    ) {
        return queryFactory.
            select(new QPostCardResponse(
                post.id,
                post.author.imageUrl,
                post.author.nickname,
                post.title,
                post.contents,
                post.createdDate,
                JPAExpressions
                    .select(comment.count())
                    .from(comment)
                    .where(comment.post.eq(post))
            ))
            .from(post)
            .leftJoin(post.author, user)
            .where(
                post.group.eq(group),
                post.type.eq(type),
                ltLastPostId(lastPostId)
            )
            .orderBy(post.id.desc())
            .limit(size)
            .fetch();
    }

    @Override
    public List<Post> findAllByGroupAndUserOrderByIdDesc(
        User loginUser, PostType type, Long lastPostId, int size
    ) {
        return queryFactory.
            selectFrom(post)
            .leftJoin(post.author, user).fetchJoin()
            .leftJoin(post.group, group).fetchJoin()
            .where(
                post.author.eq(loginUser),
                post.type.eq(type),
                ltLastPostId(lastPostId)
            )
            .orderBy(post.id.desc())
            .limit(size)
            .fetch();
    }

    private BooleanExpression ltLastPostId(Long postId) {
        if (Objects.isNull(postId)) {
            return null;
        }
        return post.id.lt(postId);
    }
}
