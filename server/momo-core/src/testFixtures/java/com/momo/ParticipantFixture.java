package com.momo;

import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.NICKNAME;

import com.momo.domain.achievementrate.entity.ParticipantAchievementRate;
import com.momo.domain.group.dto.ParticipantResponse;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.user.domain.User;

public class ParticipantFixture {

    public static Participant getParticipant(Group group, User user) {
        return Participant.builder()
            .group(group)
            .user(user)
            .build();
    }

    public static Participant getParticipant(Group group, User user, ParticipantAchievementRate rate) {
        return Participant.builder()
            .group(group)
            .user(user)
            .achievementRate(rate)
            .build();
    }

    public static Participant getParticipantWithId(Group group, User user) {
        INCREASE_ID++;
        return Participant.builder()
            .id(INCREASE_ID)
            .group(group)
            .user(user)
            .build();
    }

    public static Participant getParticipantWithId(Group group, User user, ParticipantAchievementRate rate) {
        INCREASE_ID++;
        return Participant.builder()
            .id(INCREASE_ID)
            .group(group)
            .user(user)
            .achievementRate(rate)
            .build();
    }

    public static ParticipantResponse getParticipantResponse() {
        return ParticipantResponse.builder()
            .userId(1L)
            .participantId(1L)
            .imageUrl(IMAGE_URL)
            .nickname(NICKNAME)
            .attendanceRate(100)
            .build();
    }
}
