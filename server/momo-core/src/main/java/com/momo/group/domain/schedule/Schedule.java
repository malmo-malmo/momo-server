package com.momo.group.domain.schedule;

import com.momo.common.entity.BaseEntity;
import com.momo.group.domain.Group;
import com.momo.user.domain.User;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_tb_fk_schedule"))
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk_schedule"))
    private User author;

    private String title;

    private boolean isOffline;

    private LocalDateTime startDateTime;

    @Lob
    private String contents;

    private boolean attendanceCheck = false;

    @Builder
    public Schedule(Long id, Group group, User author, String title, boolean isOffline,
        LocalDateTime startDateTime, String contents) {
        this.id = id;
        this.group = group;
        this.author = author;
        this.title = title;
        this.isOffline = isOffline;
        this.startDateTime = startDateTime;
        this.contents = contents;
    }

    public static Schedule create(Schedule schedule, Group group, User user) {
        return Schedule.builder()
            .group(group)
            .author(user)
            .title(schedule.getTitle())
            .isOffline(schedule.isOffline())
            .startDateTime(schedule.getStartDateTime())
            .contents(schedule.getContents())
            .build();
    }

    public void updateAttendanceCheck(boolean attendanceCheck) {
        this.attendanceCheck = attendanceCheck;
    }

    public boolean isSameSchedule(Schedule schedule) {
        return this.id.equals(schedule.getId());
    }
}
