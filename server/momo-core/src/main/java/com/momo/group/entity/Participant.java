package com.momo.group.entity;

import com.momo.common.entity.BaseEntity;
import com.momo.achievementrate.entity.ParticipantAchievementRate;
import com.momo.user.entity.User;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = ParticipantListener.class)
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_tb_fk_participant"))
    private Group group;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk_participant"))
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "participant_achievement_rate_fk_participant"))
    private ParticipantAchievementRate achievementRate;

    @Builder
    public Participant(Long id, Group group, User user, ParticipantAchievementRate achievementRate) {
        this.id = id;
        this.group = group;
        this.user = user;
        this.achievementRate = achievementRate;
    }

    public static Participant create(User user, Group group) {
        return Participant.builder()
            .user(user)
            .group(group)
            .build();
    }

    public void updateAchievementRate(ParticipantAchievementRate achievementRate) {
        if (Objects.isNull(achievementRate)) {
            return;
        }
        this.achievementRate = achievementRate;
    }
}
