package com.momo.domain.group.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Groups;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.dto.GroupSearchConditionRequest;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    private final UserRepository userRepository;

    public Long create(User user, GroupCreateRequest groupCreateRequest) {
        Groups group = Groups.create(user, groupCreateRequest.toEntity(), groupCreateRequest.getIsUniversity());
        Groups savedGroup = groupRepository.save(group);
        Participant participant = Participant.create(user, savedGroup);
        participantRepository.save(participant);
        return savedGroup.getId();
    }

    @Transactional(readOnly = true)
    public GroupResponse findById(User user, Long groupId) {
        return groupRepository.findGroupAndParticipantCntAndAuthorityById(user, groupId);
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageBySearchCondition(GroupSearchConditionRequest request) {
        List<Category> categories = Category.listOf(request.getCategories());
        PageRequest page = PageRequest.of(request.getPage(), request.getSize());
        return groupRepository
            .findAllBySearchConditionOrderByCreatedDateDesc(request.getCities(), categories, page);
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserUniversity(User user, int page, int size) {
        return groupRepository
            .findAllByUniversityOrderByCreatedDateDesc(user.getUniversity(), PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserDistrict(User user, int page, int size) {
        return groupRepository
            .findAllByDistrictOrderByCreatedDateDesc(user.getDistrict(), PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public List<GroupCardResponse> findPageByUserCategories(User user, int page, int size) {
        return groupRepository
            .findAllByCategoriesOrderByCreatedDateDesc(
                user.getFavoriteCategories().toCategories(),
                PageRequest.of(page, size)
            );
    }

    public void updateManagerByUserId(User user, Long groupId, Long userId) {
        Groups group = getGroupById(groupId);
        validateGroupManager(group, user);
        User participant = getUserById(userId);
        validateParticipant(group, participant);
        group.updateManager(participant);
    }

    public void endGroupById(User user, Long groupId) {
        Groups group = getGroupById(groupId);
        validateGroupManager(group, user);
        group.endGroup();
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateGroupManager(Groups group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public void validateParticipant(Groups group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
