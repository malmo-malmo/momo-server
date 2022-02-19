package com.momo.domain.group.dto;

import com.momo.domain.group.entity.Participant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantResponse {

    private Long participantId;
    private String imageUrl;
    private String nickname;
    private int attendanceRate;

    @Builder
    public ParticipantResponse(Long participantId, String imageUrl, String nickname, int attendanceRate) {
        this.participantId = participantId;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.attendanceRate = attendanceRate;
    }

    private static ParticipantResponse of(Participant participant) {
        return ParticipantResponse.builder()
            .participantId(participant.getId())
            .imageUrl(participant.getUser().getImageUrl())
            .nickname(participant.getUser().getNickname())
            .attendanceRate(participant.getAchievementRate().getRate().intValue())
            .build();
    }

    public static List<ParticipantResponse> listOf(List<Participant> participants) {
        return participants.stream()
            .map(ParticipantResponse::of)
            .collect(Collectors.toList());
    }
}