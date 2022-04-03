package com.momo.schedule.entity;

import com.momo.common.entity.BaseEntity;
import com.momo.group.entity.Participant;
import java.util.List;
import java.util.stream.Collectors;
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
public class Attendance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "schedule_fk_attendance"))
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "participant_fk_attendance"))
    private Participant participant;

    private boolean isAttend;

    @Builder
    public Attendance(Long id, Schedule schedule, Participant participant, boolean isAttend) {
        this.id = id;
        this.schedule = schedule;
        this.participant = participant;
        this.isAttend = isAttend;
    }

    private static Attendance create(Attendance attendance, Schedule schedule) {
        return Attendance.builder()
            .participant(attendance.getParticipant())
            .schedule(schedule)
            .isAttend(attendance.isAttend())
            .build();
    }

    public static List<Attendance> createAttendances(List<Attendance> attendances,
        Schedule schedule) {
        return attendances.stream()
            .map(attendance -> Attendance.create(attendance, schedule))
            .collect(Collectors.toList());
    }

    public void updateAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }
}
