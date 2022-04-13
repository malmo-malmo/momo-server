package com.momo.group.application;

import static org.springframework.data.domain.PageRequest.of;

import com.momo.group.application.dto.request.GroupSearchConditionRequest;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.search.GroupSearchEngine;
import com.momo.user.domain.User;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupsService {

    private final GroupRepository groupRepository;
    private final GroupSearchEngine groupSearchEngine;

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
            loginUser, loginUser.getUniversity(), lastGroupId, size
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
}
