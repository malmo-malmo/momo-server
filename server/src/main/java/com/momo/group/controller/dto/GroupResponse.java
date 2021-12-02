package com.momo.group.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
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

    private Long participantCnt;

    private Boolean isParticipant;

    @QueryProjection
    public GroupResponse(Long id, Long managerId, String name, String imageUrl, LocalDate startDate,
        String university, String city, String district, boolean isOffline, String introduction,
        Long participantCnt, Boolean isParticipant) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.university = university;
        this.city = city;
        this.district = district;
        this.isOffline = isOffline;
        this.introduction = introduction;
        this.participantCnt = participantCnt;
        this.isParticipant = isParticipant;
    }
}
