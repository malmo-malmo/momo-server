package com.momo.domain.group.repository;

import static com.momo.domain.group.entity.QGroup.group;
import static com.momo.domain.group.entity.QParticipant.participant;

import com.momo.domain.group.entity.Participant;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.QParticipatingGroupCardResponse;
import com.momo.domain.user.entity.User;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.CaseBuilder.Cases;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Override
    public List<Participant> findAllByIdsAndUser(List<Long> participantIds, User manager) {
        if (Objects.isNull(participantIds) || participantIds.isEmpty()) {
            return new ArrayList<>();
        }
        return queryFactory
            .selectFrom(participant)
            .where(participant.id.in(participantIds)
                .and(participant.group.manager.eq(manager)))
            .innerJoin(participant.group, group)
            .orderBy(provideIdOrder(new ArrayList<>(participantIds)))
            .fetch();
    }

    private OrderSpecifier<Integer> provideIdOrder(List<Long> participantIds) {
        Long firstId = participantIds.remove(0);
        Cases<Integer, NumberExpression<Integer>> expression = Expressions.cases().when(participant.id.eq(firstId))
            .then(1);
        int i = 2;
        for (Long participantId : participantIds) {
            expression.when(participant.id.eq(participantId)).then(i++);
        }
        return new OrderSpecifier<>(Order.ASC, expression.otherwise(1000));
    }
}
