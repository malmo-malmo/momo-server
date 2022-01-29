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
public class ParticipatingGroupSummaryResponse {

    private Long id;
    private String name;
    private Category category;

    @Builder
    public ParticipatingGroupSummaryResponse(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    private static ParticipatingGroupSummaryResponse of(Participant participant) {
        return ParticipatingGroupSummaryResponse.builder()
            .id(participant.getGroup().getId())
            .name(participant.getGroup().getName())
            .category(participant.getGroup().getCategory())
            .build();
    }

    public static List<ParticipatingGroupSummaryResponse> listOf(List<Participant> participants) {
        return participants.stream()
            .map(ParticipatingGroupSummaryResponse::of)
            .collect(Collectors.toList());
    }
}
