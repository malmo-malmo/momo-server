package com.momo.domain.group.service.impl;

import static org.springframework.data.domain.PageRequest.of;

import com.momo.domain.aws.service.S3UploadService;
import com.momo.domain.aws.util.GenerateUploadPathUtil;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.group.service.GroupService;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    public GroupResponse create(User user, GroupCreateRequest groupCreateRequest) throws IOException {
        Group group = groupRepository.save(
            Group.create(user, groupCreateRequest.toEntity(), groupCreateRequest.getIsUniversity()));
        Long groupId = group.getId();

        String imageUrl = s3UploadService
            .upload(groupCreateRequest.getImage(), GenerateUploadPathUtil.getGroupImage(groupId));
        group.updateImage(imageUrl);

        Participant participant = Participant.create(user, group);
        participantRepository.save(participant);
        return findById(user, groupId);
    }

    @Transactional(readOnly = true)
    public GroupResponse findById(User user, Long groupId) {
        return groupRepository.findGroupAndParticipantCntAndAuthorityById(user, groupId);
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageBySearchCondition(User user, GroupSearchConditionRequest request) {
        PageRequest page = of(request.getPage(), request.getSize());
        return groupRepository.findAllBySearchConditionOrderByCreatedDateDesc(user, request, page);
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserUniversity(User user, int page, int size) {
        return groupRepository
            .findAllByUniversityOrderByCreatedDateDesc(user, user.getLocation().getUniversity(), of(page, size));
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserDistrict(User user, int page, int size) {
        return groupRepository
            .findAllByDistrictOrderByCreatedDateDesc(user, user.getLocation().getDistrict(), of(page, size));
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserCategories(User user, int page, int size) {
        return groupRepository.findAllByCategoriesOrderByCreatedDateDesc(
            user,
            user.getFavoriteCategories().toCategories(),
            of(page, size)
        );
    }

    public void updateManagerByUserId(User user, Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        validateGroupManager(group, user);
        User participant = getUserById(userId);
        validateParticipant(group, participant);
        group.updateManager(participant);
    }

    public void endGroupById(User user, Long groupId) {
        Group group = getGroupById(groupId);
        validateGroupManager(group, user);
        group.endGroup();
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
