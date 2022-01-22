package com.momo.domain.management.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.management.service.impl.ManagementServiceImpl;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.user.entity.User;
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
    private PostRepository postRepository;

    private ManagementService managementService;

    @BeforeEach
    void setUp() {
        managementService = new ManagementServiceImpl(participantRepository, postRepository);
    }

    @Test
    void 유저가_참여한_모임_수를_조회한다() {
        Long expected = 1L;
        User user = User.builder().id(1L).build();

        given(participantRepository.countAllByUser(any())).willReturn(expected);

        ParticipatingGroupCountResponse actual = managementService.findParticipatingGroupCountByUser(user);

        verify(participantRepository).countAllByUser(any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getCount()).isEqualTo(expected)
        );
    }
}
