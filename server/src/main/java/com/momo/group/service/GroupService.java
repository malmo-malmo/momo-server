package com.momo.group.service;

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

    public Long create(User user, GroupCreateRequest groupCreateRequest) {
        Groups group = groupRepository
            .save(Groups.create(user, groupCreateRequest.toEntity(), groupCreateRequest.getIsUniversity()));
        participantRepository.save(Participant.create(user, group));
        return group.getId();
    }

    @Transactional(readOnly = true)
    public GroupResponse find(User user, Long groupId) {
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
}
