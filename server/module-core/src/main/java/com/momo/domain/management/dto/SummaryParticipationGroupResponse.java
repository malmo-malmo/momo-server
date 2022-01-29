package com.momo.domain.management.dto;

import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Participant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SummaryParticipationGroupResponse {

    private Long groupId;
    private String groupName;
    private Category groupCategory;

    @Builder
    public SummaryParticipationGroupResponse(Long groupId, String groupName, Category groupCategory) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupCategory = groupCategory;
    }

    private static SummaryParticipationGroupResponse of(Participant participant) {
        return SummaryParticipationGroupResponse.builder()
            .groupId(participant.getGroup().getId())
            .groupName(participant.getGroup().getName())
            .groupCategory(participant.getGroup().getCategory())
            .build();
    }

    public static List<SummaryParticipationGroupResponse> listOf(List<Participant> participants) {
        return participants.stream()
            .map(SummaryParticipationGroupResponse::of)
            .collect(Collectors.toList());
    }
}
