package com.momo.domain.group.repository;

import static com.momo.domain.group.entity.QGroup.group;
import static com.momo.domain.group.entity.QParticipant.participant;

import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.QParticipatingGroupCardResponse;
import com.momo.domain.user.entity.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParticipantRepositoryCustomImpl implements ParticipantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ParticipatingGroupCardResponse> findParticipatingGroupsByUser(User user) {
        return queryFactory
            .select(new QParticipatingGroupCardResponse(
                group.id,
                group.name,
                group.imageUrl,
                group.startDate,
                group.isOffline,
                group.isEnd,
                JPAExpressions
                    .select(participant.count())
                    .from(participant)
                    .where(participant.group.eq(group))
            ))
            .from(participant)
            .leftJoin(group).on(participant.group.eq(group))
            .where(participant.user.eq(user))
            .fetch();
    }
}
