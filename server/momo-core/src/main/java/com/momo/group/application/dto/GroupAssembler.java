package com.momo.group.application.dto;

import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.request.GroupUpdateRequest;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.domain.Group;
import com.momo.user.domain.location.Location;

public class GroupAssembler {

    public static Group mapToGroupForCreate(GroupCreateRequest request) {
        return Group.builder()
            .name(request.getName())
            .category(request.getCategory())
            .startDate(request.getStartDate())
            .university(request.getUniversity())
            .recruitmentCnt(request.getRecruitmentCnt())
            .introduction(request.getIntroduction())
            .isOffline(request.getIsOffline())
            .location(new Location(request.getCity(), request.getDistrict()))
            .build();
    }

    public static Group mapToGroupForUpdate(GroupUpdateRequest request) {
        return Group.builder()
            .name(request.getName())
            .category(request.getCategory())
            .university(request.getUniversity())
            .recruitmentCnt(request.getRecruitmentCnt())
            .introduction(request.getIntroduction())
            .isOffline(request.getIsOffline())
            .location(new Location(request.getCity(), request.getDistrict()))
            .build();
    }

    public static GroupCreateResponse mapToGroupCreateResponse(Group group) {
        return GroupCreateResponse.builder()
            .id(group.getId())
            .imageUrl(group.getImageUrl())
            .build();
    }
}
