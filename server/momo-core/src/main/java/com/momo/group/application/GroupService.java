package com.momo.group.application;

import static com.momo.aws.util.GenerateUploadPathUtil.getGroupImage;
import static org.springframework.data.domain.PageRequest.of;

import com.momo.aws.service.S3UploadService;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.dto.GroupCardResponse;
import com.momo.group.application.dto.GroupCreateRequest;
import com.momo.group.application.dto.GroupCreateResponse;
import com.momo.group.application.dto.GroupResponse;
import com.momo.group.application.dto.GroupSearchConditionRequest;
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
