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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "participant_fk_user_achievement_rate"))
    private Participant participant;

    private BigDecimal rate;

    @Builder
    public ParticipantAchievementRate(Long id, Participant participant, BigDecimal rate) {
        this.id = id;
        this.participant = participant;
        this.rate = rate;
    }

    public static ParticipantAchievementRate create(Participant participant) {
        return ParticipantAchievementRate.builder()
            .participant(participant)
            .rate(BigDecimal.ZERO)
            .build();
    }
}
