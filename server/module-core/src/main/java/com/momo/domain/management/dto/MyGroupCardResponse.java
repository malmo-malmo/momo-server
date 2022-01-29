package com.momo.domain.management.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyGroupCardResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private Long achievementRate;
    List<String> participants;
}
