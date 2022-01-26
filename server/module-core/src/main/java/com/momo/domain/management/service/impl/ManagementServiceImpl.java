package com.momo.domain.management.service.impl;

import static com.momo.domain.post.entity.PostType.NORMAL;
import static org.springframework.data.domain.PageRequest.of;

import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.management.service.ManagementService;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagementServiceImpl implements ManagementService {

    private final ParticipantRepository participantRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public ParticipatingGroupCountResponse findParticipatingGroupCountByUser(User loginUser) {
        Long count = participantRepository.countAllByUser(loginUser);
        return ParticipatingGroupCountResponse.of(count);
    }

    @Transactional(readOnly = true)
    public List<ParticipatingGroupCardResponse> findParticipatingGroupsByUser(User loginUser) {
        return participantRepository.findParticipatingGroupsByUser(loginUser);
    }

    @Transactional(readOnly = true)
    public List<MyPostCardResponse> findMyPostsByUser(User loginUser, int page, int size) {
        List<Post> posts = postRepository
            .findAllWithGroupAndAuthorByUserAndTypeOrderByCreatedDateDesc(loginUser, NORMAL, of(page, size));
        return MyPostCardResponse.listOf(posts);
    }
}
