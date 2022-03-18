package com.momo.domain.group.service.impl;

import static com.momo.domain.aws.util.GenerateUploadPathUtil.getGroupImage;
import static org.springframework.data.domain.PageRequest.of;

import com.momo.domain.aws.service.S3UploadService;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupCreateResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.group.search.GroupSearchEngine;
import com.momo.domain.group.service.GroupService;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupSearchEngine groupSearchEngine;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    public GroupCreateResponse createGroup(User loginUser, GroupCreateRequest request) {
        Group group = groupRepository.save(
            Group.create(loginUser, request.toEntity(), request.getIsUniversity())
        );

        String imageUrl = s3UploadService.upload(request.getImage(), getGroupImage(group.getId()));
        group.updateImage(imageUrl);

        participantRepository.save(Participant.create(loginUser, group));

        return GroupCreateResponse.of(group);
    }

    @Transactional(readOnly = true)
    public GroupResponse findGroupById(User user, Long groupId) {
        return groupRepository.findDetailByGroupId(user, groupId);
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageBySearchConditionV1(User loginUser, GroupSearchConditionRequest request) {
        PageRequest page = of(request.getPage(), request.getSize());
        return groupRepository.findAllBySearchConditionOrderByCreatedDateDesc(loginUser, request, page);
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageBySearchConditionV2(User loginUser, GroupSearchConditionRequest request) {
        PageRequest page = of(request.getPage(), request.getSize());

        if (Objects.isNull(request.getGroupName())) {
            return groupRepository.findAllByCitiesAndCategoriesOrderByCreatedDateDesc(
                loginUser, request.getCities(), request.getCategories(), page
            );
        }

        return groupSearchEngine.searchByNameLikeAndCitiesAndCategories(
            request.getGroupName(), request.getCities(), request.getCategories(), loginUser, page
        );
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserUniversity(User loginUser, Long lastGroupId, int size) {
        return groupRepository.findByUniversityOrderByIdDesc(
            loginUser, loginUser.getLocation().getUniversity(), lastGroupId, size
        );
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserDistrict(User loginUser, Long lastGroupId, int size) {
        return groupRepository.findByDistrictOrderByIdDesc(
            loginUser, loginUser.getLocation().getDistrict(), lastGroupId, size
        );
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserCategories(User loginUser, Long lastGroupId, int size) {
        return groupRepository.findByCategoriesOrderByIdDesc(
            loginUser, loginUser.getFavoriteCategories().toCategories(), lastGroupId, size
        );
    }

    public void updateManagerByUserId(User loginUser, Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        validateGroupManager(group, loginUser);
        User participant = getUserById(userId);
        validateParticipant(group, participant);
        group.updateManager(participant);
    }

    public void endGroupById(User loginUser, Long groupId) {
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
