package com.momo.post.domain.repository;

import static com.momo.post.domain.model.QComment.comment;
import static com.momo.post.domain.model.QPost.post;
import static com.momo.user.domain.model.QUser.user;

import com.momo.group.domain.model.Groups;
import com.momo.post.controller.dto.PostCardResponse;
import com.momo.post.controller.dto.QPostCardResponse;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostType;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostCardResponse> findAllByGroupAndTypeOrderByCreatedDateDesc(Groups group, PostType type,
        Pageable pageable) {
        List<PostCardResponse> content = queryFactory.
            select(new QPostCardResponse(
                post.id,
                post.author.image,
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
