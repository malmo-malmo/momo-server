package com.momo.group.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.controller.dto.GroupCardResponse;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.controller.dto.GroupResponse;
import com.momo.group.controller.dto.GroupSearchConditionRequest;
import com.momo.group.domain.model.Category;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.model.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import java.util.List;
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
            .findAllByCategoriesOrderByCreatedDateDesc(user.getCategories(), PageRequest.of(page, size));
    }

    public void updateManagerByUserId(User user, Long groupId, Long userId) {
        Groups group = getGroupById(groupId);
        validateGroupManager(group, user);
        User participant = getUserById(userId);
        validateGroupParticipant(participant, group);
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
        if (group.isNotManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public void validateGroupParticipant(User user, Groups group) {
        if (!participantRepository.existsByUserAndGroup(user, group)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
