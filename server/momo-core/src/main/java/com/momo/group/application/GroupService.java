package com.momo.group.application;

import static com.momo.aws.util.GenerateUploadPathUtil.getGroupImagePath;

import com.momo.aws.service.S3UploadService;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.dto.GroupAssembler;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.request.GroupUpdateRequest;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.application.dto.response.GroupImageUpdateResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.user.domain.User;
import com.momo.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
            Group.create(loginUser, GroupAssembler.mapToGroupForCreate(request))
        );

        String imageUrl = s3UploadService.upload(request.getImage(), getGroupImagePath(group.getId()));
        group.updateImage(imageUrl);

        participantRepository.save(Participant.create(loginUser, group));

        return GroupAssembler.mapToGroupCreateResponse(group);
    }

    @Transactional(readOnly = true)
    public GroupResponse findGroup(User user, Long groupId) {
        return groupRepository.findDetailByGroupId(user, groupId);
    }

    public void updateGroupInformation(User loginUser, GroupUpdateRequest request) {
        Group group = getGroupById(request.getId());
        group.validateManager(loginUser);
        group.validateRecruitmentCnt(request.getRecruitmentCnt());

        group.update(GroupAssembler.mapToGroupForUpdate(request));
    }

    public GroupImageUpdateResponse updateGroupImage(User loginUser, Long groupId, MultipartFile imageFile) {
        Group group = getGroupById(groupId);
        group.validateManager(loginUser);

        String imageUrl = s3UploadService.upload(imageFile, getGroupImagePath(group.getId()));
        group.updateImage(imageUrl);

        return GroupAssembler.mapToGroupImageUpdateResponse(imageUrl);
    }

    public void updateManager(User loginUser, Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        group.validateManager(loginUser);

        User participant = getUserById(userId);
        validateParticipant(group, participant);

        group.updateManager(participant);
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }

    public void endGroup(User loginUser, Long groupId) {
        Group group = getGroupById(groupId);
        group.validateManager(loginUser);
        group.end();
    }

    public void deleteGroupImage(User loginUser, Long groupId) {
        Group group = getGroupById(groupId);
        group.validateManager(loginUser);

        group.updateImage(null);
    }

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
