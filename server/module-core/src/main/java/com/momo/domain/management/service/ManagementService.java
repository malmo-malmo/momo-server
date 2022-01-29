package com.momo.domain.management.service;

import com.momo.domain.management.dto.ManagingGroupCardResponse;
import com.momo.domain.management.dto.ManagingGroupSummaryResponse;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.management.dto.ParticipatingGroupSummaryResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ManagementService {

    ParticipatingGroupCountResponse findParticipatingGroupCountByUser(User loginUser);

    List<ParticipatingGroupCardResponse> findParticipatingGroupsByUser(User loginUser);

    List<ParticipatingGroupSummaryResponse> findParticipatingGroupsSummaryByUser(User loginUser);

    List<ManagingGroupCardResponse> findManagingGroupsByUser(User loginUser);

    List<ManagingGroupSummaryResponse> findManagingGroupsSummaryByUser(User loginUser);

    List<MyPostCardResponse> findMyPostsByUser(User loginUser, int page, int size);
}
