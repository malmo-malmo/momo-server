package com.momo;

import com.momo.domain.achievementrate.entity.ParticipantAchievementRate;
import com.momo.domain.group.dto.ParticipantResponse;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.user.entity.User;

public class ParticipantFixture {

    private static final String NICKNAME = "닉네임";
    private static final String IMAGE_URL = "이미지 URL";

    private static Long INCREASE_ID = 0L;

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
            .imageUrl(IMAGE_URL)
            .nickname(NICKNAME)
            .attendanceRate(100)
            .build();
    }
}
