package com.momo.group.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Groups extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User manager;

    private String groupName;

    private String groupImgUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate startDate;

    private String university;

    @Enumerated(EnumType.STRING)
    private Location location;

    @Lob
    private String introduction;

    private Long recruitmentCnt;


    /*
    TODO : 서브쿼리로 바꾸기
    private Long participantCnt;

    private Long scheduleCnt;

    private Long attendanceCnt;
    */

    @Column(name = "offline_flag")
    private Boolean isOffline;

    @Column(name = "end_flag")
    private Boolean isEnd;

    @Builder
    public Groups(Long id, User manager, String groupName, String groupImgUrl, Category category,
        LocalDate startDate, String university, Location location, String introduction, Long recruitmentCnt,
        Boolean isOffline, Boolean isEnd) {
        this.id = id;
        this.manager = manager;
        this.groupName = groupName;
        this.groupImgUrl = groupImgUrl;
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
            .groupName(group.getGroupName())
            .groupImgUrl(group.getGroupImgUrl())
            .category(group.getCategory())
            .startDate(group.getStartDate())
            .university(group.getUniversity())
            .location(group.getLocation())
            .introduction(group.getIntroduction())
            .recruitmentCnt(group.getRecruitmentCnt())
            .isOffline(group.getIsOffline())
            .isEnd(false)
            .build();
    }

    public void validateIsManager(User user) {
        if (!this.getManager().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.GROUP_NOTICE_UNAUTHORIZED);
        }
    }
}
