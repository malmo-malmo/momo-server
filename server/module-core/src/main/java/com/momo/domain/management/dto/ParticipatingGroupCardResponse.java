package com.momo.domain.management.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipatingGroupCardResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private LocalDate startDate;
    private boolean isOffline;
    private boolean isEnd;
    private Long participantCnt;

    @Builder
    @QueryProjection
    public ParticipatingGroupCardResponse(Long id, String name, String imageUrl, LocalDate startDate, boolean isOffline,
        boolean isEnd, Long participantCnt) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.isOffline = isOffline;
        this.isEnd = isEnd;
        this.participantCnt = participantCnt;
    }
}
