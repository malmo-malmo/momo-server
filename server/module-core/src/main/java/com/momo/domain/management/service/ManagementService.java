package com.momo.domain.management.service;

import com.momo.domain.management.dto.MyGroupCardResponse;
import com.momo.domain.management.dto.MyGroupSummaryResponse;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipationGroupCardResponse;
import com.momo.domain.management.dto.ParticipationGroupCountResponse;
import com.momo.domain.management.dto.ParticipationGroupSummaryResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ManagementService {

    ParticipationGroupCountResponse findParticipationGroupCountByUser(User loginUser);

    List<ParticipationGroupCardResponse> findParticipationGroupsByUser(User loginUser);

    List<ParticipationGroupSummaryResponse> findParticipationGroupsSummaryByUser(User loginUser);

    List<MyGroupCardResponse> findMyGroupsByUser(User loginUser);

    List<MyGroupSummaryResponse> findMyGroupsSummaryByUser(User loginUser);

    List<MyPostCardResponse> findMyPostsByUser(User loginUser, int page, int size);
}
