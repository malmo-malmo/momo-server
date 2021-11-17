package com.momo.group.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.common.uploader.AwsS3Uploader;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.model.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    public Long create(User loginUser, GroupCreateRequest req) {
        User user = findByUser(loginUser);
        Groups group = groupRepository.save(Groups.create(user, req.toEntity()));
        participantRepository.save(Participant.create(user, group));
        return group.getId();
    }

    public User findByUser(User user) {
        return userRepository.findById(user.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USER_ID));
    }
}
