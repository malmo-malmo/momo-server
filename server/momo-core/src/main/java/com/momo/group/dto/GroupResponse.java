package com.momo.group.dto;

import com.momo.user.domain.model.Location;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupResponse {

    private Long id;
    private Long managerId;
    private String name;
    private String imageUrl;
    private LocalDate startDate;
    private String university;
    private String city;
    private String district;
    private boolean isOffline;
    private String introduction;
    private int recruitmentCnt;
    private boolean isEnd;
    private Long participantCnt;
    private boolean isParticipant;

    @Builder
    @QueryProjection
    public GroupResponse(Long id, Long managerId, String name, String imageUrl, LocalDate startDate,
        Location location, boolean isOffline, String introduction,
        int recruitmentCnt, boolean isEnd, Long participantCnt, boolean isParticipant) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.university = location.getUniversity();
        this.city = location.getCity().getName();
        this.district = location.getDistrict();
        this.isOffline = isOffline;
        this.introduction = introduction;
        this.recruitmentCnt = recruitmentCnt;
        this.isEnd = isEnd;
        this.participantCnt = participantCnt;
        this.isParticipant = isParticipant;
    }
}
