package com.momo.group.controller.dto;

import com.momo.group.domain.model.Groups;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private LocalDate startDate;

    private int participantCnt;

    private String university;

    private String city;

    private String district;

    private boolean isOffline;

    private String introduction;

    private boolean isParticipant;

    private boolean isManager;

    @Builder
    public GroupResponse(Long id, String name, String imageUrl, LocalDate startDate, int participantCnt,
        String university, String city, String district, boolean isOffline, String introduction, boolean isParticipant,
        boolean isManager) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.participantCnt = participantCnt;
        this.university = university;
        this.city = city;
        this.district = district;
        this.isOffline = isOffline;
        this.introduction = introduction;
        this.isParticipant = isParticipant;
        this.isManager = isManager;
    }

    public static GroupResponse of(Groups group, boolean isManager, boolean isParticipant) {
        return GroupResponse.builder()
            .id(group.getId())
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .startDate(group.getStartDate())
            .participantCnt(group.getParticipantCnt())
            .university(group.getUniversity())
            .city(group.getCity())
            .district(group.getDistrict())
            .isOffline(group.isOffline())
            .introduction(group.getIntroduction())
            .isManager(isManager)
            .isParticipant(isParticipant)
            .build();
    }
}
