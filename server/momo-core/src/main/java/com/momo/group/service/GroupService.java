package com.momo.group.service;

import com.momo.group.dto.GroupCardResponse;
import com.momo.group.dto.GroupCreateRequest;
import com.momo.group.dto.GroupCreateResponse;
import com.momo.group.dto.GroupResponse;
import com.momo.group.dto.GroupSearchConditionRequest;
import com.momo.user.entity.User;
import java.util.List;

public interface GroupService {

    GroupCreateResponse createGroup(User loginUser, GroupCreateRequest groupCreateRequest);

    GroupResponse findGroupById(User loginUser, Long groupId);

    List<GroupCardResponse> findPageBySearchConditionV1(User loginUser, GroupSearchConditionRequest request);

    List<GroupCardResponse> findPageBySearchConditionV2(User loginUser, GroupSearchConditionRequest request);

    List<GroupCardResponse> findPageByUserUniversity(User loginUser, Long lastGroupId, int size);

    List<GroupCardResponse> findPageByUserDistrict(User loginUser, Long lastGroupId, int size);

    List<GroupCardResponse> findPageByUserCategories(User loginUser, Long lastGroupId, int size);

    void updateManagerByUserId(User loginUser, Long groupId, Long userId);

    void endGroupById(User loginUser, Long groupId);
}
