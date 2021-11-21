package com.momo.group.domain.repository;

import static com.momo.group.domain.model.QParticipant.participant;
import static com.momo.schedule.domain.model.QAttendance.attendance;
import static com.momo.user.domain.model.QUser.user;

import com.momo.group.controller.dto.ParticipantResponse;
import com.momo.group.controller.dto.QParticipantResponse;
import com.momo.group.domain.model.Groups;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ParticipantRepositoryCustomImpl implements ParticipantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ParticipantResponse> findParticipantAndAttendanceRateByGroup(Groups group, Long scheduleCnt) {
        List<ParticipantResponse> responses = queryFactory
            .select(new QParticipantResponse(
                participant.user.image,
                participant.user.nickname,
                JPAExpressions
                    .select(attendance.count())
                    .from(attendance)
                    .where(attendance.user.eq(participant.user).and(attendance.isAttendance.eq(true)))
            ))
            .from(participant)
            .leftJoin(user).on(participant.user.eq(user))
            .where(participant.group.eq(group))
            .fetch();

        //TODO : 이 부분 한방 쿼리로 해결하기.
        for (ParticipantResponse response : responses) {
            response.calculateAttendanceRate(scheduleCnt);
        }
        return responses;
    }
}
