package com.momo.domain.group.service;

import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.user.entity.User;
import java.io.IOException;
import java.util.List;

public interface GroupService {

    GroupResponse create(User user, GroupCreateRequest groupCreateRequest) throws IOException;

    GroupResponse findById(User user, Long groupId);

    List<GroupCardResponse> findPageBySearchCondition(User user, GroupSearchConditionRequest request);

    List<GroupCardResponse> findPageByUserUniversity(User user, int page, int size);

    List<GroupCardResponse> findPageByUserDistrict(User user, int page, int size);

    List<GroupCardResponse> findPageByUserCategories(User user, int page, int size);

    void updateManagerByUserId(User user, Long groupId, Long userId);

    void endGroupById(User user, Long groupId);
}
