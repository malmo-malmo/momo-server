package com.momo.domain.schedule.domain.model;

import com.momo.domain.common.domain.BaseEntity;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.user.domain.model.User;
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
    @JoinColumn(foreignKey = @ForeignKey(name = "groups_fk_schedule"))
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "user_fk_schedule"))
    private User author;

    private String title;

    private boolean isOffline;

    private LocalDateTime startDateTime;

    @Lob
    private String contents;

    private boolean attendanceCheck;

    @Builder
    public Schedule(Long id, Groups group, User author, String title, boolean isOffline,
        LocalDateTime startDateTime, String contents, boolean attendanceCheck) {
        this.id = id;
        this.group = group;
        this.author = author;
        this.title = title;
        this.isOffline = isOffline;
        this.startDateTime = startDateTime;
        this.contents = contents;
        this.attendanceCheck = attendanceCheck;
    }

    public static Schedule create(Schedule schedule, Groups group, User user) {
        return Schedule.builder()
            .group(group)
            .author(user)
            .title(schedule.getTitle())
            .isOffline(schedule.isOffline())
            .startDateTime(schedule.getStartDateTime())
            .contents(schedule.getContents())
            .attendanceCheck(false)
            .build();
    }

    public void updateAttendanceCheck(boolean attendanceCheck) {
        this.attendanceCheck = attendanceCheck;
    }
}
