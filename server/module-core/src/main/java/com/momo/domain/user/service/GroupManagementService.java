package com.momo.domain.user.service;

import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.user.dto.ParticipatingGroupCardResponse;
import com.momo.domain.user.dto.ParticipatingGroupCountResponse;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupManagementService {

    private final ParticipantRepository participantRepository;

    @Transactional(readOnly = true)
    public ParticipatingGroupCountResponse findParticipatingGroupCountByUser(User loginUser) {
        Long count = participantRepository.countAllByUser(loginUser);
        return ParticipatingGroupCountResponse.of(count);
    }

    @Transactional(readOnly = true)
    public List<ParticipatingGroupCardResponse> findParticipatingGroupsByUser(User loginUser) {
        return participantRepository.findParticipatingGroupsByUser(loginUser);
    }
}
