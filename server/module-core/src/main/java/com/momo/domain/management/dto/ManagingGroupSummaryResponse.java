package com.momo.domain.management.dto;

import com.momo.domain.group.entity.Group;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagingGroupSummaryResponse {

    private Long id;
    private String name;

    @Builder
    public ManagingGroupSummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static ManagingGroupSummaryResponse of(Group group) {
        return ManagingGroupSummaryResponse.builder()
            .id(group.getId())
            .name(group.getName())
            .build();
    }

    public static List<ManagingGroupSummaryResponse> listOf(List<Group> groups) {
        return groups.stream()
            .map(ManagingGroupSummaryResponse::of)
            .collect(Collectors.toList());
    }
}
