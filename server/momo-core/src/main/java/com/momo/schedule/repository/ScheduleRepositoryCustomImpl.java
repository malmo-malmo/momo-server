package com.momo.schedule.repository;

import static com.momo.group.domain.participant.QParticipant.participant;
import static com.momo.schedule.entity.QAttendance.attendance;
import static com.momo.schedule.entity.QSchedule.schedule;
import static com.momo.user.domain.QUser.user;

import com.momo.group.domain.Group;
import com.momo.schedule.dto.GroupScheduleResponse;
import com.momo.schedule.dto.QGroupScheduleResponse;
import com.momo.schedule.entity.Schedule;
import com.momo.user.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupScheduleResponse> findAllByGroupOrderByStartDateTimeDesc(
        Group group, Long userId, LocalDateTime lastScheduleStartDateTime, int size
    ) {
        return selectGroupScheduleClause(userId)
            .from(schedule)
            .leftJoin(schedule.author, user)
            .where(
                schedule.group.eq(group),
                ltLastScheduleStartDateTime(lastScheduleStartDateTime)
            )
            .orderBy(schedule.startDateTime.desc())
            .limit(size)
            .fetch();
    }

    private BooleanExpression ltLastScheduleStartDateTime(LocalDateTime startDateTime) {
        if (Objects.isNull(startDateTime)) {
            return null;
        }
        return schedule.startDateTime.lt(startDateTime);
    }

    @Override
    public List<Schedule> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime,
        User user) {
        return queryFactory
            .selectFrom(schedule)
            .where(schedule.group.id.in(
                JPAExpressions
                    .select(participant.group.id)
                    .from(participant)
                    .where(participant.user.eq(user))
            ).and(schedule.startDateTime.between(startDateTime, endDateTime)))
            .orderBy(schedule.startDateTime.asc())
            .fetch();
    }

    @Override
    public GroupScheduleResponse findGroupResponseById(Long scheduleId, Long userId) {
        return selectGroupScheduleClause(userId)
            .from(schedule)
            .leftJoin(schedule.author, user)
            .where(schedule.id.eq(scheduleId))
            .fetchOne();
    }

    private JPAQuery<GroupScheduleResponse> selectGroupScheduleClause(Long userId) {
        return queryFactory
            .select(new QGroupScheduleResponse(
                schedule.id,
                schedule.author.imageUrl,
                schedule.author.nickname,
                schedule.title,
                schedule.isOffline,
                schedule.startDateTime,
                schedule.contents,
                schedule.attendanceCheck,
                JPAExpressions
                    .select(attendance.isAttend.nullif(false))
                    .from(attendance)
                    .leftJoin(attendance.participant, participant)
                    .where(
                        attendance.schedule.eq(schedule),
                        attendance.participant.user.id.eq(userId)
                    )
            ));
    }
}
