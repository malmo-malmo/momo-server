package com.momo.group.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.user.domain.model.User;
import java.time.LocalDate;
import javax.persistence.Entity;
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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "user_fk_groups"))
    private User manager;

    private String name;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate startDate;

    private String university;

    private String city;

    private String district;

    @Lob
    private String introduction;

    private int recruitmentCnt;

    private boolean isOffline;

    private boolean isEnd;

    @Builder
    public Groups(Long id, User manager, String name, String imageUrl, Category category, LocalDate startDate,
        String university, String city, String district, String introduction, int recruitmentCnt, boolean isOffline,
        boolean isEnd) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.startDate = startDate;
        this.university = university;
        this.city = city;
        this.district = district;
        this.introduction = introduction;
        this.recruitmentCnt = recruitmentCnt;
        this.isOffline = isOffline;
        this.isEnd = isEnd;
    }

    public static Groups create(User user, Groups group, boolean isUniversity) {
        return Groups.builder()
            .manager(user)
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .category(group.getCategory())
            .startDate(group.getStartDate())
            .university(isUniversity ? user.getUniversity() : null)
            .city(group.getCity())
            .district(group.getDistrict())
            .introduction(group.getIntroduction())
            .recruitmentCnt(group.getRecruitmentCnt())
            .isOffline(group.isOffline())
            .isEnd(false)
            .build();
    }

    public boolean isManager(User user) {
        return user.isSameUser(manager);
    }

    public void updateManager(User user) {
        this.manager = user;
    }

    public void endGroup() {
        this.isEnd = true;
    }
}
