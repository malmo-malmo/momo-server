package com.momo.group.controller.dto;

import com.momo.group.domain.model.Groups;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupResponse {

    private String name;

    private String imageUrl;

    private LocalDate startDate;

    private int participantCnt;

    private String university;

    private String location;

    private boolean isOffline;

    private String introduction;

    private boolean isParticipant;

    private boolean isManager;

    @Builder
    public GroupResponse(String name, String imageUrl, LocalDate startDate, int participantCnt, String university,
        String location, boolean isOffline, String introduction, boolean isParticipant, boolean isManager) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.participantCnt = participantCnt;
        this.university = university;
        this.location = location;
        this.isOffline = isOffline;
        this.introduction = introduction;
        this.isParticipant = isParticipant;
        this.isManager = isManager;
    }

    public static GroupResponse of(Groups group, boolean isManager, boolean isParticipant) {
        return GroupResponse.builder()
            .name(group.getName())
            .imageUrl(group.getImageUrl())
            .startDate(group.getStartDate())
            .participantCnt(group.getParticipantCnt())
            .university(group.getUniversity())
            .location(group.getLocation().getName())
            .isOffline(group.isOffline())
            .introduction(group.getIntroduction())
            .isManager(isManager)
            .isParticipant(isParticipant)
            .build();
    }
}
