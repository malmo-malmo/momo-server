package com.momo.domain.management.service;

import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.user.entity.User;
import java.util.List;

public interface ManagementService {

    ParticipatingGroupCountResponse findParticipatingGroupCountByUser(User loginUser);

    List<ParticipatingGroupCardResponse> findParticipatingGroupsByUser(User loginUser);

    List<MyPostCardResponse> findMyPostsByUser(User loginUser, int page, int size);
}