package com.momo.domain.user.service;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.user.dto.FavoriteGroupCreateRequest;
import com.momo.domain.user.entity.FavoriteGroup;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.FavoriteGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("관심 모임 서비스 테스트")
public class FavoriteGroupServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private FavoriteGroupRepository favoriteGroupRepository;

    @InjectMocks
    private FavoriteGroupService favoriteGroupService;

    @Test
    void 관심_모임으로_등록한다() {
        FavoriteGroupCreateRequest request = FavoriteGroupCreateRequest.builder().groupId(1L).build();
        User user = User.builder().id(1L).build();
        Group group = Group.builder().id(1L).build();
        FavoriteGroup favoriteGroup = FavoriteGroup.builder().id(1L).build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(favoriteGroupRepository.save(any())).willReturn(favoriteGroup);

        favoriteGroupService.create(user, request);

        verify(groupRepository).findById(any());
        verify(favoriteGroupRepository).save(any());
    }
}
