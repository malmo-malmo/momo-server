package com.momo.group.domain;

import com.momo.achievementrate.entity.GroupAchievementRate;
import com.momo.common.entity.BaseEntity;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.category.Category;
import com.momo.user.domain.User;
import com.momo.user.domain.location.Location;
import java.time.LocalDate;
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

    private String university;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Lob
    private String introduction;

    @Embedded
    private Location location;

    private int recruitmentCnt;

    private boolean isOffline;

    private boolean isEnd;

    @Builder
    public Group(
        Long id, User manager, GroupAchievementRate achievementRate, String name,
        String imageUrl, Category category, LocalDate startDate, String university,
        Location location, String introduction, int recruitmentCnt, boolean isOffline, boolean isEnd
    ) {
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
        this.university = university;
        this.isOffline = isOffline;
        this.isEnd = isEnd;
    }

    public static Group create(User user, Group group) {
        return Group.builder()
            .manager(user)
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .university(group.getUniversity())
            .category(group.getCategory())
            .startDate(group.getStartDate())
            .location(Location.create(group.getLocation()))
            .introduction(group.getIntroduction())
            .recruitmentCnt(group.getRecruitmentCnt())
            .isOffline(group.isOffline())
            .isEnd(false)
            .build();
    }

    public void update(Group group) {
        this.name = group.getName();
        this.category = group.getCategory();
        this.recruitmentCnt = group.getRecruitmentCnt();
        this.introduction = group.getIntroduction();
        this.university = group.getUniversity();
        this.isOffline = group.isOffline();
        this.location.update(group.getLocation());
    }

    public void updateAchievementRate(GroupAchievementRate achievementRate) {
        this.achievementRate = achievementRate;
    }

    public void validateManager(User user) {
        if (!this.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public boolean isManager(User user) {
        if (Objects.isNull(user)) {
            return false;
        }

        return user.equals(manager);
    }

    public void validateRecruitmentCnt(int recruitmentCnt) {
        if (this.recruitmentCnt > recruitmentCnt) {
            throw new CustomException(ErrorCode.INVALID_GROUP_RECRUITMENT_COUNT);
        }
    }

    public void updateManager(User user) {
        this.manager = user;
    }

    public void updateImage(String imageUrl) {
        if (Objects.isNull(imageUrl)) {
            this.imageUrl = null;
            return;
        }

        this.imageUrl = imageUrl;
    }

    public void end() {
        this.isEnd = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Group)) {
            return false;
        }

        Group group = (Group) o;

        return this.id != null ? this.id.equals(group.getId()) : group.getId() == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
