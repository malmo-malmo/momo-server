package com.momo.domain.schedule.repository;

import static com.momo.domain.group.entity.QGroups.groups;
import static com.momo.domain.group.entity.QParticipant.participant;
import static com.momo.domain.schedule.entity.QAttendance.attendance;
import static com.momo.domain.schedule.entity.QSchedule.schedule;
import static com.momo.domain.user.entity.QUser.user;

import com.momo.domain.group.entity.Groups;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.QGroupScheduleResponse;
import com.momo.domain.schedule.entity.Schedule;
import com.momo.domain.user.entity.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupScheduleResponse> findAllByGroupAndUserOrderByCreatedDateDesc(Groups group, Long userId,
                                                                                   Pageable pageable) {
        List<GroupScheduleResponse> content = queryFactory
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
                    .select(attendance.isAttend)
                    .from(attendance)
                    .where(attendance.schedule.eq(schedule).and(attendance.userId.eq(userId)))
            ))
            .from(schedule)
            .leftJoin(user).on(schedule.author.eq(user))
            .where(schedule.group.eq(group))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        JPAQuery<Schedule> countQuery = queryFactory
            .select(schedule)
            .from(schedule)
            .where(schedule.group.eq(group));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount).getContent();
    }

    @Override
    public List<Schedule> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime,
                                                        User user) {
        return queryFactory
            .selectFrom(schedule)
            .leftJoin(schedule.group, groups).fetchJoin()
            .where(schedule.group.in(
                JPAExpressions
                    .select(participant.group)
                    .from(participant)
                    .where(participant.user.eq(user))
            ).and(schedule.startDateTime.between(startDateTime, endDateTime)))
            .orderBy(schedule.startDateTime.asc())
            .fetch();
    }
}