package com.momo.management.service;

import com.momo.management.dto.MyGroupCardResponse;
import com.momo.management.dto.MyGroupSummaryResponse;
import com.momo.management.dto.MyPostCardResponse;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.management.dto.ParticipationGroupCountResponse;
import com.momo.management.dto.ParticipationGroupSummaryResponse;
import com.momo.user.domain.model.User;
import java.util.List;

public interface ManagementService {

    ParticipationGroupCountResponse findParticipationGroupCountByUser(User loginUser);

    List<ParticipationGroupCardResponse> findParticipationGroupsByUser(User loginUser);

    List<ParticipationGroupSummaryResponse> findParticipationGroupsSummaryByUser(User loginUser);

    List<MyGroupCardResponse> findMyGroupsByUser(User loginUser);

    List<MyGroupSummaryResponse> findMyGroupsSummaryByUser(User loginUser);

    List<MyPostCardResponse> findMyPostsByUser(User loginUser, Long lastPostId, int size);
}
