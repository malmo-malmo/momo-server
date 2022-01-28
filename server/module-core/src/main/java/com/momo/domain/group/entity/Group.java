package com.momo.domain.group.entity;

import com.momo.domain.common.entity.BaseEntity;
import com.momo.domain.district.entity.City;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String name;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate startDate;

    private String university;

    @Enumerated(EnumType.STRING)
    private City city;

    private String district;

    @Lob
    private String introduction;

    private int recruitmentCnt;

    private boolean isOffline;

    private boolean isEnd;

    @Builder
    public Group(Long id, User manager, String name, String imageUrl, Category category, LocalDate startDate,
        String university, City city, String district, String introduction, int recruitmentCnt, boolean isOffline,
        boolean isEnd, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        super(createdDate, lastModifiedDate);
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

    public static Group create(User user, Group group, boolean isUniversity) {
        return Group.builder()
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

    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
