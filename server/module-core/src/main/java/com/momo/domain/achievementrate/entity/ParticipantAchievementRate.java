package com.momo.domain.achievementrate.entity;

import com.momo.domain.common.entity.BaseEntity;
import com.momo.domain.group.entity.Participant;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantAchievementRate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal rate;

    @Builder
    public ParticipantAchievementRate(Long id, BigDecimal rate) {
        this.id = id;
        this.rate = rate;
    }

    public static ParticipantAchievementRate create() {
        return ParticipantAchievementRate.builder()
            .rate(BigDecimal.ZERO)
            .build();
    }
}
