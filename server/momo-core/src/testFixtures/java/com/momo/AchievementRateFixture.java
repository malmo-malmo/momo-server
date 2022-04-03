package com.momo;

import static com.momo.common.FixtureComponents.INCREASE_ID;

import com.momo.achievementrate.entity.GroupAchievementRate;
import com.momo.achievementrate.entity.ParticipantAchievementRate;
import java.math.BigDecimal;

public class AchievementRateFixture {

    public static ParticipantAchievementRate getParticipantAchievementRate(BigDecimal rate) {
        return ParticipantAchievementRate.builder()
            .rate(rate)
            .build();
    }

    public static ParticipantAchievementRate getParticipantAchievementRateWithId(BigDecimal rate) {
        INCREASE_ID++;
        return ParticipantAchievementRate.builder()
            .id(INCREASE_ID)
            .rate(rate)
            .build();
    }

    public static GroupAchievementRate getGroupAchievementRate(BigDecimal rate) {
        return GroupAchievementRate.builder()
            .rate(rate)
            .build();
    }

    public static GroupAchievementRate getGroupAchievementRateWithId(BigDecimal rate) {
        INCREASE_ID++;
        return GroupAchievementRate.builder()
            .id(INCREASE_ID)
            .rate(rate)
            .build();
    }
}
