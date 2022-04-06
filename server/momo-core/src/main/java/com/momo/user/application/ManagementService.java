package com.momo.user.application;

import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.group.domain.post.Post;
import com.momo.group.domain.post.PostType;
import com.momo.group.domain.repository.PostRepository;
import com.momo.user.application.dto.response.MyGroupCardResponse;
import com.momo.user.application.dto.response.MyGroupSummaryResponse;
import com.momo.user.application.dto.response.MyPostCardResponse;
import com.momo.user.application.dto.response.ParticipationGroupCardResponse;
import com.momo.user.application.dto.response.ParticipationGroupCountResponse;
import com.momo.user.application.dto.response.ParticipationGroupSummaryResponse;
import com.momo.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagementService {

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
        List<Post> posts = postRepository.findAllByGroupAndUserOrderByIdDesc(loginUser, PostType.NORMAL, lastPostId,
            size);
        return MyPostCardResponse.listOf(posts);
    }
}
