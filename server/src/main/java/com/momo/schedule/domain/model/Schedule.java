package com.momo.schedule.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.group.domain.model.Groups;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @JoinColumn(name = "group_id")
    private Groups group;

    private String title;

    private boolean isOffline;

    private LocalDateTime startDateTime;

    @Lob
    private String Contents;

    @Builder
    public Schedule(Long id, Groups group, String title, boolean isOffline, LocalDateTime startDateTime,
        String contents) {
        this.id = id;
        this.group = group;
        this.title = title;
        this.isOffline = isOffline;
        this.startDateTime = startDateTime;
        Contents = contents;
    }

    public static Schedule create(Schedule schedule, Groups group) {
        return Schedule.builder()
            .group(group)
            .title(schedule.getTitle())
            .isOffline(schedule.isOffline())
            .startDateTime(schedule.getStartDateTime())
            .contents(schedule.getContents())
            .build();
    }
}
