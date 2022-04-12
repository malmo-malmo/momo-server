package com.momo.management.service.impl;

import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.management.dto.MyGroupCardResponse;
import com.momo.management.dto.MyGroupSummaryResponse;
import com.momo.management.dto.MyPostCardResponse;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.management.dto.ParticipationGroupCountResponse;
import com.momo.management.dto.ParticipationGroupSummaryResponse;
import com.momo.management.service.ManagementService;
import com.momo.post.entity.Post;
import com.momo.post.repository.PostRepository;
import com.momo.user.domain.User;
import com.momo.post.entity.PostType;
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
        List<Post> posts = postRepository.findAllByGroupAndUserOrderByIdDesc(loginUser, PostType.NORMAL, lastPostId, size);
        return MyPostCardResponse.listOf(posts);
    }
}
