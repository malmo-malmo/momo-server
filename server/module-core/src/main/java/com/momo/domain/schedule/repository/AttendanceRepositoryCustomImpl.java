package com.momo.domain.schedule.repository;

import static com.momo.domain.schedule.entity.QAttendance.attendance;

import com.momo.domain.schedule.entity.Attendance;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.CaseBuilder.Cases;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttendanceRepositoryCustomImpl implements AttendanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Attendance> findAllByIds(List<Long> attendanceIds) {
        if (Objects.isNull(attendanceIds) || attendanceIds.isEmpty()) {
            return new ArrayList<>();
        }
        return queryFactory
            .selectFrom(attendance)
            .where(attendance.id.in(attendanceIds))
            .orderBy(provideIdOrder(new ArrayList<>(attendanceIds)))
            .fetch();
    }

    private OrderSpecifier<Integer> provideIdOrder(List<Long> attendanceIds) {
        Long firstId = attendanceIds.remove(0);
        Cases<Integer, NumberExpression<Integer>> expression = Expressions.cases().when(attendance.id.eq(firstId))
            .then(1);
        int i = 2;
        for (Long attendanceId : attendanceIds) {
            expression.when(attendance.id.eq(attendanceId)).then(i++);
        }
        return new OrderSpecifier<>(Order.ASC, expression.otherwise(1000));
    }
}
