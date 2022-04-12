package com.momo;

import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.NICKNAME;

import com.momo.achievementrate.entity.ParticipantAchievementRate;
import com.momo.group.application.dto.ParticipantResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.user.domain.User;

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
