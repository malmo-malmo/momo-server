package com.momo.domain.group.service;

import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupCreateResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface GroupService {

    GroupCreateResponse createGroup(User loginUser, GroupCreateRequest groupCreateRequest);

    GroupResponse findGroupById(User loginUser, Long groupId);

    List<GroupCardResponse> findPageBySearchConditionV1(User loginUser, GroupSearchConditionRequest request);

    List<GroupCardResponse> findPageBySearchConditionV2(User loginUser, GroupSearchConditionRequest request);

    List<GroupCardResponse> findPageByUserUniversity(User loginUser, int page, int size);

    List<GroupCardResponse> findPageByUserDistrict(User loginUser, int page, int size);

    List<GroupCardResponse> findPageByUserCategories(User loginUser, int page, int size);

    void updateManagerByUserId(User loginUser, Long groupId, Long userId);

    void endGroupById(User loginUser, Long groupId);
}
