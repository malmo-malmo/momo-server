package com.momo.group.application;

import static com.momo.aws.util.GenerateUploadPathUtil.getGroupImage;
import static org.springframework.data.domain.PageRequest.of;

import com.momo.aws.service.S3UploadService;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.dto.GroupAssembler;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.request.GroupSearchConditionRequest;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.group.domain.search.GroupSearchEngine;
import com.momo.user.domain.User;
import com.momo.user.domain.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    public GroupCreateResponse createGroup(User loginUser, GroupCreateRequest request) {
        Group group = groupRepository.save(
            Group.create(loginUser, GroupAssembler.mapToGroup(request), request.getIsUniversity())
        );

        String imageUrl = s3UploadService.upload(request.getImage(), getGroupImage(group.getId()));
        group.updateImage(imageUrl);

        participantRepository.save(Participant.create(loginUser, group));

        return GroupAssembler.mapToGroupCreateResponse(group);
    }

    @Transactional(readOnly = true)
    public GroupResponse findGroup(User user, Long groupId) {
        return groupRepository.findDetailByGroupId(user, groupId);
    }

    public void updateManager(User loginUser, Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        validateGroupManager(group, loginUser);
        User participant = getUserById(userId);
        validateParticipant(group, participant);
        group.updateManager(participant);
    }

    public void endGroup(User loginUser, Long groupId) {
        Group group = getGroupById(groupId);
        validateGroupManager(group, loginUser);
        group.endGroup();
    }

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    private void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}