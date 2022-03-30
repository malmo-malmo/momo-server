package com.momo.favorite.repository;

import static com.momo.favorite.entity.QFavoriteGroup.favoriteGroup;
import static com.momo.group.entity.QGroup.group;
import static com.momo.group.entity.QParticipant.participant;

import com.momo.favorite.dto.FavoriteGroupCardResponse;
import com.momo.favorite.dto.QFavoriteGroupCardResponse;
import com.momo.group.dto.QGroupCardResponse;
import com.momo.user.domain.User;
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
            .leftJoin(favoriteGroup.group, group)
            .where(favoriteGroup.user.eq(user))
            .orderBy(favoriteGroup.createdDate.desc())
            .fetch();
    }
}
