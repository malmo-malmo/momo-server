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
    public List<PostCardResponse> findAllWithAuthorByGroupAndTypeOrderByCreatedDateDesc(Group group, PostType type,
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

    @Override
    public List<Post> findAllWithGroupAndAuthorByUserAndTypeOrderByCreatedDateDesc(User loginUser, PostType type,
        Pageable pageable) {
        List<Post> content = queryFactory.
            selectFrom(post)
            .leftJoin(post.author, user).fetchJoin()
            .leftJoin(post.group, group).fetchJoin()
            .where(post.author.eq(loginUser), post.type.eq(type))
            .orderBy(post.createdDate.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Post> countQuery = queryFactory
            .select(post)
            .from(post)
            .where(post.author.eq(loginUser));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount).getContent();
    }
}
