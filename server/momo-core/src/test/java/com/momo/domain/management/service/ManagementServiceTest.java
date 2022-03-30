package com.momo.domain.management.service;

import static com.momo.UserFixture.getUserWithId;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.group.repository.GroupRepository;
import com.momo.group.repository.ParticipantRepository;
import com.momo.management.dto.ParticipationGroupCountResponse;
import com.momo.management.service.ManagementService;
import com.momo.management.service.impl.ManagementServiceImpl;
import com.momo.post.repository.PostRepository;
import com.momo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("관리 서비스 테스트")
public class ManagementServiceTest extends ServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private PostRepository postRepository;

    private ManagementService managementService;

    @BeforeEach
    void setUp() {
        managementService = new ManagementServiceImpl(participantRepository, groupRepository, postRepository);
    }

    @Test
    void 유저가_참여한_모임_수를_조회한다() {
        Long expected = 1L;
        User user = getUserWithId();

        given(participantRepository.countAllByUser(any())).willReturn(expected);

        ParticipationGroupCountResponse actual = managementService.findParticipationGroupCountByUser(user);

        verify(participantRepository).countAllByUser(any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getCount()).isEqualTo(expected)
        );
    }
}
