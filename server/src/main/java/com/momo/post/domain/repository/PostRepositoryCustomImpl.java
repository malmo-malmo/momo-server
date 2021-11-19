package com.momo.post.domain.repository;

import static com.momo.post.domain.model.QPost.post;
import static com.momo.post.domain.model.QPostComment.postComment;
import static com.momo.user.domain.model.QUser.user;
import static com.querydsl.core.types.ExpressionUtils.count;

import com.momo.group.domain.model.Groups;
import com.momo.post.controller.dto.PostCardResponse;
import com.momo.post.controller.dto.PostResponse;
import com.momo.post.controller.dto.QPostCardResponse;
import com.momo.post.controller.dto.QPostResponse;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostType;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PostResponse> findByIdAndCountComment(Long postId) {
        PostResponse result = queryFactory.select(new QPostResponse(
                post.id,
                post.user.image,
                post.user.nickname,
                post.title,
                post.contents,
                getCommentCnt()
            )).from(post)
            .where(post.id.eq(postId))
            .leftJoin(post.user, user)
            .fetchOne();
        return Optional.ofNullable(result);
    }

    @Override
    public Page<PostCardResponse> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type,
        Pageable pageable) {
        List<PostCardResponse> results = queryFactory
            .select(new QPostCardResponse(
                post.id,
                post.user.image,
                post.user.nickname,
                post.title,
                post.contents,
                getCommentCnt()
            ))
            .from(post)
            .leftJoin(post.user, user)
            .where(
                post.group.eq(group),
                post.type.eq(type)
            )
            .orderBy(post.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPQLQuery<Post> countQuery = JPAExpressions.selectFrom(post)
            .where(post.group.eq(group));

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);
    }

    public Expression<Long> getCommentCnt() {
        return JPAExpressions.select(count(postComment.id))
            .from(postComment)
            .where(postComment.post.eq(post));
    }
}
