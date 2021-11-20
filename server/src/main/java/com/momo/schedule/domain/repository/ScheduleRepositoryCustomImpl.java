package com.momo.schedule.domain.repository;

import static com.momo.schedule.domain.model.QAttendance.attendance;
import static com.momo.schedule.domain.model.QSchedule.schedule;

import com.momo.group.domain.model.Groups;
import com.momo.schedule.controller.dto.GroupScheduleResponse;
import com.momo.schedule.controller.dto.QGroupScheduleResponse;
import com.momo.schedule.domain.model.Schedule;
import com.momo.user.domain.model.User;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GroupScheduleResponse> findAllByGroupAndUserOrderByCreatedDateDesc(Groups group, User user,
        Pageable pageable) {
        List<GroupScheduleResponse> content = queryFactory
            .select(new QGroupScheduleResponse(
                schedule.id,
                schedule.author.image,
                schedule.author.nickname,
                schedule.title,
                schedule.isOffline,
                schedule.startDateTime,
                schedule.contents,
                schedule.isAttendanceCheck,
                JPAExpressions
                    .select(attendance.isAttendance)
                    .from(attendance)
                    .where(attendance.schedule.eq(schedule).and(attendance.user.eq(user)))
            ))
            .from(schedule)
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
}
