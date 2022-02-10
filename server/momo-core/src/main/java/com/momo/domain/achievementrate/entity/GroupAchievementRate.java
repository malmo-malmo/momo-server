package com.momo.domain.achievementrate.entity;

import com.momo.domain.common.entity.BaseEntity;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private BigDecimal rate;

    @Builder
    public GroupAchievementRate(Long id, BigDecimal rate) {
        this.id = id;
        this.rate = rate;
    }

    public static GroupAchievementRate create() {
        return GroupAchievementRate.builder()
            .rate(BigDecimal.ZERO)
            .build();
    }
}
