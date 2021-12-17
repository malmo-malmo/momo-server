package com.momo.domain.post.domain.repository;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.post.domain.model.Post;
import com.momo.domain.post.domain.model.PostType;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.QPostCardResponse;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.momo.domain.post.domain.model.QComment.comment;
import static com.momo.domain.post.domain.model.QPost.post;
import static com.momo.domain.user.domain.model.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostCardResponse> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type,
                                                                              Pageable pageable) {
        List<PostCardResponse> content = queryFactory.
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
            .leftJoin(user).on(post.author.eq(user))
            .where(post.group.eq(group).and(post.type.eq(type)))
            .orderBy(post.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Post> countQuery = queryFactory
            .select(post)
            .from(post)
            .where(post.group.eq(group).and(post.type.eq(type)));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount).getContent();
    }
}
