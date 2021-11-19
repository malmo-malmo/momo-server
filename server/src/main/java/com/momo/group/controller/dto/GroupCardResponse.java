package com.momo.group.controller.dto;

import com.momo.group.domain.model.Groups;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupCardResponse {

    private String name;

    private String imageUrl;

    private LocalDate startDate;

    private int participantCnt;

    private boolean isOffline;

    @Builder
    public GroupCardResponse(String name, String imageUrl, LocalDate startDate, int participantCnt, boolean isOffline) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.participantCnt = participantCnt;
        this.isOffline = isOffline;
    }

    private static GroupCardResponse of(Groups group) {
        return GroupCardResponse.builder()
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .startDate(group.getStartDate())
            .participantCnt(group.getParticipantCnt())
            .isOffline(group.isOffline())
            .build();
    }

    public static List<GroupCardResponse> listOf(List<Groups> groups) {
        return groups.stream().map(GroupCardResponse::of).collect(Collectors.toList());
    }
}
