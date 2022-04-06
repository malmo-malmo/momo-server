package com.momo.user.application.dto.response;

import com.momo.group.domain.category.Category;
import com.momo.group.domain.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipationGroupSummaryResponse {

    private Long id;
    private String name;
    private Category category;

    @Builder
    public ParticipationGroupSummaryResponse(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    private static ParticipationGroupSummaryResponse of(Participant participant) {
        return ParticipationGroupSummaryResponse.builder()
            .id(participant.getGroup().getId())
            .name(participant.getGroup().getName())
            .category(participant.getGroup().getCategory())
            .build();
    }

    public static List<ParticipationGroupSummaryResponse> listOf(List<Participant> participants) {
        return participants.stream()
            .map(ParticipationGroupSummaryResponse::of)
            .collect(Collectors.toList());
    }
}
