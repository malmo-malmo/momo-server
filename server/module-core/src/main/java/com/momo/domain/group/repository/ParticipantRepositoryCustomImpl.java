package com.momo.domain.group.repository;

import static com.momo.domain.group.entity.QGroup.group;
import static com.momo.domain.group.entity.QParticipant.participant;

import com.momo.domain.group.entity.Participant;
import com.momo.domain.management.dto.ParticipationGroupCardResponse;
import com.momo.domain.management.dto.QParticipationGroupCardResponse;
import com.momo.domain.user.entity.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParticipantRepositoryCustomImpl implements ParticipantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ParticipationGroupCardResponse> findParticipationGroupsByUser(User user) {
        return queryFactory
            .select(new QParticipationGroupCardResponse(
                participant.group.id,
                participant.group.name,
                participant.group.imageUrl,
                participant.group.startDate,
                participant.group.isOffline,
                participant.group.isEnd,
                JPAExpressions
                    .select(participant.count())
                    .from(participant)
                    .where(participant.group.eq(group))
            ))
            .from(participant)
            .leftJoin(participant.group, group)
            .where(participant.user.eq(user))
            .orderBy(participant.group.createdDate.asc())
            .fetch();
    }

    @Override
    public List<Participant> findAllWithNotManagingGroupByUser(User user) {
        return queryFactory
            .selectFrom(participant)
            .leftJoin(participant.group, group).fetchJoin()
            .where(participant.user.eq(user), participant.group.manager.ne(user))
            .orderBy(participant.group.createdDate.asc())
            .fetch();
    }
}
