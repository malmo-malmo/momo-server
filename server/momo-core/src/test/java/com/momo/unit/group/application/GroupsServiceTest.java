package com.momo.unit.group.application;

import static com.momo.GroupFixture.getGroupCardResponse;
import static com.momo.GroupFixture.getGroupSearchConditionRequest;
import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.group.application.GroupsService;
import com.momo.group.application.dto.request.GroupSearchConditionRequest;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.search.GroupSearchEngine;
import com.momo.user.domain.User;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("모임 서비스 테스트")
public class GroupsServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupSearchEngine groupSearchEngine;

    private GroupsService groupsService;

    private User manager;

    @BeforeEach
    void setUp() {
        manager = getUserWithId();
        groupsService = new GroupsService(groupRepository, groupSearchEngine);
    }

    @Test
    void 모임_검색_테스트_성공_검색_키워드_O() {
        GroupSearchConditionRequest request = getGroupSearchConditionRequest("키워드");
        GroupCardResponse response = getGroupCardResponse();

        given(groupSearchEngine.searchByNameLikeAndCitiesAndCategories(any(), any(), any(), any(), any()))
            .willReturn(List.of(response));

        List<GroupCardResponse> expected = groupsService.findPageBySearchConditionV2(manager, request);

        verify(groupSearchEngine).searchByNameLikeAndCitiesAndCategories(any(), any(), any(), any(), any());
        assertThat(expected.size()).isEqualTo(1);
    }

    @Test
    void 모임_검색_테스트_성공_검색_키워드_X() {
        GroupSearchConditionRequest request = getGroupSearchConditionRequest(null);
        GroupCardResponse response = getGroupCardResponse();

        given(groupRepository.findAllByCitiesAndCategoriesOrderByCreatedDateDesc(any(), any(), any(), any()))
            .willReturn(List.of(response));

        List<GroupCardResponse> expected = groupsService.findPageBySearchConditionV2(manager, request);

        verify(groupRepository).findAllByCitiesAndCategoriesOrderByCreatedDateDesc(any(), any(), any(), any());
        assertThat(expected.size()).isEqualTo(1);
    }
}
