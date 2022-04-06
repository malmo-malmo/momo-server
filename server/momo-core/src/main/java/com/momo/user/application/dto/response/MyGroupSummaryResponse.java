package com.momo.user.application.dto.response;

import com.momo.group.domain.Group;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyGroupSummaryResponse {

    private Long id;
    private String name;

    @Builder
    public MyGroupSummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static MyGroupSummaryResponse of(Group group) {
        return MyGroupSummaryResponse.builder()
            .id(group.getId())
            .name(group.getName())
            .build();
    }

    public static List<MyGroupSummaryResponse> listOf(List<Group> groups) {
        return groups.stream()
            .map(MyGroupSummaryResponse::of)
            .collect(Collectors.toList());
    }
}
