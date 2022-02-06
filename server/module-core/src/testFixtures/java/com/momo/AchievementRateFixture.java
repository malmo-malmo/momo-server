package com.momo;

import com.momo.domain.achievementrate.entity.GroupAchievementRate;
import com.momo.domain.achievementrate.entity.ParticipantAchievementRate;
import java.math.BigDecimal;

public class AchievementRateFixture {

    private static Long INCREASE_ID = 0L;

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
