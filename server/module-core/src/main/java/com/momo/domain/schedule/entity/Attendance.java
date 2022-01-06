package com.momo.domain.schedule.entity;

import com.momo.domain.group.entity.Group;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_tb_fk_attendance"))
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "schedule_fk_attendance"))
    private Schedule schedule;

    @Column(nullable = false)
    private Long userId;

    private boolean isAttend;

    @Builder
    public Attendance(Group group, Schedule schedule, Long userId, boolean isAttend) {
        this.group = group;
        this.schedule = schedule;
        this.userId = userId;
        this.isAttend = isAttend;
    }

    private static Attendance create(Attendance attendance, Group group, Schedule schedule) {
        return Attendance.builder()
            .group(group)
            .schedule(schedule)
            .userId(attendance.getUserId())
            .isAttend(attendance.isAttend())
            .build();
    }

    public static List<Attendance> createAttendances(List<Attendance> attendances, Group group, Schedule schedule) {
        return attendances.stream()
            .map(attendance -> Attendance.create(attendance, group, schedule))
            .collect(Collectors.toList());
    }
}
