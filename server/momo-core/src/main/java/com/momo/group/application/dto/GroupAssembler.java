package com.momo.group.application.dto;

import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.domain.Group;
import com.momo.user.domain.location.Location;

public class GroupAssembler {

    public static Group mapToGroup(GroupCreateRequest request) {
        return Group.builder()
            .name(request.getName())
            .category(request.getCategory())
            .location(Location.fromEmptyUniversity(request.getCity(), request.getDistrict()))
            .startDate(request.getStartDate())
            .recruitmentCnt(request.getRecruitmentCnt())
            .introduction(request.getIntroduction())
            .isOffline(request.getIsOffline())
            .build();
    }

    public static GroupCreateResponse mapToGroupCreateResponse(Group group) {
        return GroupCreateResponse.builder()
            .id(group.getId())
            .imageUrl(group.getImageUrl())
            .build();
    }
}
