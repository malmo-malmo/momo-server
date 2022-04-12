package com.momo.unit.group.application;

import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.GroupFixture.getGroupResponse;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.group.domain.category.Category.EMPLOYMENT;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.aws.service.S3UploadService;
import com.momo.common.ServiceTest;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.GroupService;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.group.domain.search.GroupSearchEngine;
import com.momo.user.domain.User;
import com.momo.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("모임 서비스 테스트")
public class GroupServiceTest extends ServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private S3UploadService s3UploadService;

    private GroupService groupService;

    private User manager;
    private User participant;

    @BeforeEach
    void setUp() {
        manager = getUserWithId();
        participant = getUserWithId();
        groupService = new GroupService(
            groupRepository, participantRepository, userRepository, s3UploadService
        );
    }

    @Test
    void 모임_생성_테스트() {
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(EMPLOYMENT, true);
        GroupResponse groupResponse = getGroupResponse();

        given(groupRepository.save(any())).willReturn(Group.builder().id(1L).build());
        given(participantRepository.save(any())).willReturn(Participant.builder().build());
        given(s3UploadService.upload(any(), any())).willReturn("업로드된 이미지 경로");
        given(groupRepository.findDetailByGroupId(any(), anyLong())).willReturn(groupResponse);

        GroupCreateResponse response = groupService.createGroup(manager, groupCreateRequest);

        verify(groupRepository).save(any());
        verify(participantRepository).save(any());
        Assertions.assertAll(
            () -> assertThat(response).isNotNull(),
            () -> assertThat(response.getId()).isNotNull()
        );
    }

    @Test
    void 모임_권한_양도_테스트_성공() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(userRepository.findById(any())).willReturn(of(participant));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);

        groupService.updateManager(manager, 1L, 2L);

        verify(groupRepository).findById(any());
        verify(userRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        assertThat(group.getManager().getId()).isEqualTo(participant.getId());
    }

    @Test
    void 모임_관리자가_아니면_권한_양도_테스트를_실패한다() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.updateManager(participant, 1L, 2L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_참여자가_아니면_권한_양도_테스트를_실패한다() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(userRepository.findById(any())).willReturn(of(participant));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> groupService.updateManager(manager, 1L, 2L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 모임_종료_테스트_성공() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        groupService.endGroup(manager, 1L);

        verify(groupRepository).findById(any());
        assertThat(group.isEnd()).isTrue();
    }

    @Test
    void 모임_관리자가_아니면_종료_테스트를_실패한다() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.endGroup(participant, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }
}
