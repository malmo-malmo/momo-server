package com.momo.domain.post.repository;

import static com.momo.domain.group.entity.QGroup.group;
import static com.momo.domain.post.entity.QComment.comment;
import static com.momo.domain.post.entity.QPost.post;
import static com.momo.domain.user.entity.QUser.user;

import com.momo.domain.group.entity.Group;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.QPostCardResponse;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.user.entity.User;
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
