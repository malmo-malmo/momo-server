package com.momo.domain.management.dto;

import com.momo.domain.group.entity.Group;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyGroupCardResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private int achievementRate;

    @Builder
    public MyGroupCardResponse(Long id, String name, String imageUrl, int achievementRate) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.achievementRate = achievementRate;
    }

    private static MyGroupCardResponse of(Group group) {
        return MyGroupCardResponse.builder()
            .id(group.getId())
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .achievementRate(group.getAchievementRate().getRate().intValue())
            .build();
    }

    public static List<MyGroupCardResponse> listOf(List<Group> groups) {
        return groups.stream()
            .map(MyGroupCardResponse::of)
            .collect(Collectors.toList());
    }
}
