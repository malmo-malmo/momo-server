package com.momo.domain.group;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.momo.ServiceTest;
import com.momo.domain.group.domain.model.Category;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.group.domain.model.Participant;
import com.momo.domain.group.domain.repository.GroupRepository;
import com.momo.domain.group.domain.repository.ParticipantRepository;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.service.GroupService;
import com.momo.domain.user.domain.model.User;
import com.momo.domain.user.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("모임 서비스 테스트")
public class GroupServiceTest extends ServiceTest {
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private ParticipantRepository participantRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private GroupService groupService;

    @Test
    void 모임_생성_테스트() {
        given(groupRepository.save(any())).willReturn(Groups.builder().id(1l).build());
        given(participantRepository.save(any())).willReturn(Participant.builder().build());

        Long groupId = groupService.create(
            User.builder()
                .university("서울대")
                .build(),
            GroupCreateRequest.builder()
                .isUniversity(true)
                .category(Category.EMPLOYMENT.getCode())
                .isOffline(false)
                .build());
        assertThat(groupId).isEqualTo(1l);
    }
}
