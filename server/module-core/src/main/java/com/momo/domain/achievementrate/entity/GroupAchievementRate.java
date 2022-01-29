package com.momo.domain.achievementrate.entity;

import com.momo.domain.common.entity.BaseEntity;
import com.momo.domain.group.entity.Group;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupAchievementRate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_tb_fk_group_achievement_rate"))
    private Group group;

    private BigDecimal rate;

    @Builder
    public GroupAchievementRate(Long id, Group group, BigDecimal rate) {
        this.id = id;
        this.group = group;
        this.rate = rate;
    }
}
