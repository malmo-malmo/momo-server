package com.momo.group.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.user.domain.model.Location;
import com.momo.user.domain.model.User;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.Formula;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Groups extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User manager;

    private String name;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate startDate;

    private String university;

    @Enumerated(EnumType.STRING)
    private Location location;

    @Lob
    private String introduction;

    private int recruitmentCnt;

    @Column(name = "offline_flag")
    private boolean isOffline;

    @Column(name = "end_flag")
    private boolean isEnd;

    @Formula("(select count(*) from participant p where p.group_id = id)")
    private int participantCnt;

    /*
    TODO : 서브쿼리로 바꾸기
    private Long scheduleCnt;
    private Long attendanceCnt;
    */

    @Builder
    public Groups(Long id, User manager, String name, String imageUrl, Category category,
        LocalDate startDate, String university, Location location, String introduction, int recruitmentCnt,
        boolean isOffline, boolean isEnd) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.startDate = startDate;
        this.university = university;
        this.location = location;
        this.introduction = introduction;
        this.recruitmentCnt = recruitmentCnt;
        this.isOffline = isOffline;
        this.isEnd = isEnd;
    }

    public static Groups create(User user, Groups group) {
        return Groups.builder()
            .manager(user)
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .category(group.getCategory())
            .startDate(group.getStartDate())
            .university(group.getUniversity())
            .location(group.getLocation())
            .introduction(group.getIntroduction())
            .recruitmentCnt(group.getRecruitmentCnt())
            .isOffline(group.isOffline())
            .isEnd(false)
            .build();
    }

    public boolean isManager(User user) {
        return user.isSameUser(manager);
    }
}
