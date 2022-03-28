package com.momo.domain.management.service.impl;

import static com.momo.domain.post.entity.PostType.NORMAL;

import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.management.dto.MyGroupCardResponse;
import com.momo.domain.management.dto.MyGroupSummaryResponse;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipationGroupCardResponse;
import com.momo.domain.management.dto.ParticipationGroupCountResponse;
import com.momo.domain.management.dto.ParticipationGroupSummaryResponse;
import com.momo.domain.management.service.ManagementService;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    private final ParticipantRepository participantRepository;
    private final GroupRepository groupRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public ParticipationGroupCountResponse findParticipationGroupCountByUser(User loginUser) {
        Long count = participantRepository.countAllByUser(loginUser);
        return ParticipationGroupCountResponse.of(count);
    }

    @Transactional(readOnly = true)
    public List<ParticipationGroupCardResponse> findParticipationGroupsByUser(User loginUser) {
        return participantRepository.findParticipationGroupsByUser(loginUser);
    }

    @Transactional(readOnly = true)
    public List<ParticipationGroupSummaryResponse> findParticipationGroupsSummaryByUser(User loginUser) {
        List<Participant> participants = participantRepository.findAllWithNotManagingGroupByUser(loginUser);
        return ParticipationGroupSummaryResponse.listOf(participants);
    }

    @Transactional(readOnly = true)
    public List<MyGroupCardResponse> findMyGroupsByUser(User loginUser) {
        List<Group> groups = groupRepository.findGroupAndAchievementRateByUser(loginUser);
        return MyGroupCardResponse.listOf(groups);
    }

    @Transactional(readOnly = true)
    public List<MyGroupSummaryResponse> findMyGroupsSummaryByUser(User loginUser) {
        List<Group> groups = groupRepository.findAllByManagerAndIsEnd(loginUser, false);
        return MyGroupSummaryResponse.listOf(groups);
    }

    @Transactional(readOnly = true)
    public List<MyPostCardResponse> findMyPostsByUser(User loginUser, Long lastPostId, int size) {
        List<Post> posts = postRepository.findAllByGroupAndUserOrderByIdDesc(loginUser, NORMAL, lastPostId, size);
        return MyPostCardResponse.listOf(posts);
    }
}
