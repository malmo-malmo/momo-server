package com.momo.group.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupCardResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private LocalDate startDate;

    private boolean isOffline;

    private Long participantCnt;

    @QueryProjection
    public GroupCardResponse(Long id, String name, String imageUrl, LocalDate startDate, boolean isOffline,
        Long participantCnt) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.isOffline = isOffline;
        this.participantCnt = participantCnt;
    }
}
