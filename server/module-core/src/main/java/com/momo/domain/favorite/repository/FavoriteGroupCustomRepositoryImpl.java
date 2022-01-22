package com.momo.domain.favorite.repository;

import static com.momo.domain.favorite.entity.QFavoriteGroup.favoriteGroup;
import static com.momo.domain.group.entity.QGroup.group;
import static com.momo.domain.group.entity.QParticipant.participant;

import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.dto.QFavoriteGroupCardResponse;
import com.momo.domain.group.dto.QGroupCardResponse;
import com.momo.domain.user.entity.User;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FavoriteGroupCustomRepositoryImpl implements FavoriteGroupCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<FavoriteGroupCardResponse> findAllByUserOrderByCreatedDateDesc(User user) {
        return queryFactory
            .select(new QFavoriteGroupCardResponse(
                favoriteGroup.id,
                new QGroupCardResponse(
                    favoriteGroup.group.id,
                    favoriteGroup.group.name,
                    favoriteGroup.group.imageUrl,
                    favoriteGroup.group.startDate,
                    favoriteGroup.group.isOffline,
                    JPAExpressions
                        .select(participant.count())
                        .from(participant)
                        .where(participant.group.eq(group)),
                    Expressions.TRUE
                )
            ))
            .from(favoriteGroup)
            .leftJoin(group).on(favoriteGroup.group.eq(group))
            .where(favoriteGroup.user.eq(user))
            .orderBy(favoriteGroup.createdDate.desc())
            .fetch();
    }
}
