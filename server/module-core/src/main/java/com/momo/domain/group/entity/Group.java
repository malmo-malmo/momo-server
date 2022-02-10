package com.momo.domain.group.entity;

import com.momo.domain.achievementrate.entity.GroupAchievementRate;
import com.momo.domain.common.entity.BaseEntity;
import com.momo.domain.user.entity.Location;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "group_tb")
@EntityListeners(value = GroupListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "user_fk_group_tb"))
    private User manager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_achievement_rate_fk_group_tb"))
    private GroupAchievementRate achievementRate;

    private String name;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate startDate;

    @Embedded
    private Location location;

    @Lob
    private String introduction;

    private int recruitmentCnt;

    private boolean isOffline;

    private boolean isEnd;

    @Builder
    public Group(Long id, User manager, GroupAchievementRate achievementRate, String name, String imageUrl,
        Category category, LocalDate startDate, Location location, String introduction,
        int recruitmentCnt, boolean isOffline, boolean isEnd, LocalDateTime createdDate,
        LocalDateTime lastModifiedDate) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.manager = manager;
        this.achievementRate = achievementRate;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.startDate = startDate;
        this.location = location;
        this.introduction = introduction;
        this.recruitmentCnt = recruitmentCnt;
        this.isOffline = isOffline;
        this.isEnd = isEnd;
    }

    private static Location hasUniversityLocation(boolean isNotUniversity, Location location) {
        if(isNotUniversity) {
            return location;
        }
        return Location.fromEmptyUniversity(location);
    }
    public static Group create(User user, Group group, boolean isUniversity) {
        return Group.builder()
            .manager(user)
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .category(group.getCategory())
            .startDate(group.getStartDate())
            .location(hasUniversityLocation(!isUniversity, user.getLocation()))
            .introduction(group.getIntroduction())
            .recruitmentCnt(group.getRecruitmentCnt())
            .isOffline(group.isOffline())
            .isEnd(false)
            .build();
    }

    public void updateAchievementRate(GroupAchievementRate achievementRate) {
        if (Objects.isNull(achievementRate)) {
            return;
        }
        this.achievementRate = achievementRate;
    }

    public boolean isManager(User user) {
        if (Objects.isNull(user)) {
            return false;
        }
        return user.isSameUser(manager);
    }

    public void updateManager(User user) {
        if (Objects.isNull(user)) {
            return;
        }
        this.manager = user;
    }

    public void updateImage(String imageUrl) {
        if (Objects.isNull(imageUrl)) {
            this.imageUrl = null;
            return;
        }
        this.imageUrl = imageUrl;
    }

    public void endGroup() {
        this.isEnd = true;
    }
}
