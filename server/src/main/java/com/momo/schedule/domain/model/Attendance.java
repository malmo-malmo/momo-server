package com.momo.schedule.domain.model;

import com.momo.group.domain.model.Groups;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @JoinColumn(name = "group_id")
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column(nullable = false)
    private Long userId;

    private boolean isAttend;

    @Builder
    public Attendance(Groups group, Schedule schedule, Long userId, boolean isAttend) {
        this.group = group;
        this.schedule = schedule;
        this.userId = userId;
        this.isAttend = isAttend;
    }

    public static List<Attendance> create(List<Attendance> attendances, Groups group, Schedule schedule) {
        for (Attendance attendance : attendances) {
            attendance.group = group;
            attendance.schedule = schedule;
        }
        return attendances;
    }
}
