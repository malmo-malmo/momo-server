package com.momo.domain.group.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.aws.service.S3UploadService;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.group.service.impl.GroupServiceImpl;
import com.momo.domain.user.entity.Location;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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
        manager = User.builder().id(1L).location(Location.builder().university("서울대학교").build()).build();
        participant = User.builder().id(2L).build();
        groupService = new GroupServiceImpl(groupRepository, participantRepository, userRepository, s3UploadService);
    }

    @Test
    void 모임_생성_테스트() throws IOException {
        MultipartFile file = new MockMultipartFile("image", "test.png", null,
            new ClassPathResource("upload-test.png").getInputStream());
        GroupCreateRequest groupCreateRequest = GroupCreateRequest.builder()
            .category(Category.EMPLOYMENT)
            .isUniversity(true)
            .city(City.SEOUL)
            .isOffline(true)
            .image(file)
            .build();
        Long savedGroupId = 1L;
        given(groupRepository.save(any())).willReturn(Group.builder().id(1L).build());
        given(participantRepository.save(any())).willReturn(Participant.builder().build());
        given(s3UploadService.upload(file, "group")).willReturn("업로드된 이미지 경로");
        given(groupRepository.findGroupAndParticipantCntAndAuthorityById(any(User.class), anyLong()))
            .willReturn(GroupResponse.builder().id(savedGroupId).location(Location.builder().city(City.BUSAN).build()).build());

        GroupResponse response = groupService.create(manager, groupCreateRequest);

        verify(groupRepository).save(any());
        verify(participantRepository).save(any());
        Assertions.assertAll(
            () -> assertThat(response).isNotNull(),
            () -> assertThat(response.getId()).isEqualTo(savedGroupId)
        );
    }

    @Test
    void 모임_권한_양도_테스트_성공() {
        Group group = Group.builder()
            .manager(manager)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(userRepository.findById(any())).willReturn(of(participant));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);

        groupService.updateManagerByUserId(manager, 1L, 2L);

        verify(groupRepository).findById(any());
        verify(userRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        assertThat(group.getManager().getId()).isEqualTo(participant.getId());
    }

    @Test
    void 모임_관리자가_아니면_권한_양도_테스트를_실패한다() {
        Group group = Group.builder()
            .manager(manager)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.updateManagerByUserId(participant, 1L, 2L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임_참여자가_아니면_권한_양도_테스트를_실패한다() {
        Group group = Group.builder()
            .manager(manager)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(userRepository.findById(any())).willReturn(of(participant));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> groupService.updateManagerByUserId(manager, 1L, 2L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 모임_종료_테스트_성공() {
        Group group = Group.builder()
            .manager(manager)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        groupService.endGroupById(manager, 1L);

        verify(groupRepository).findById(any());
        assertThat(group.isEnd()).isTrue();
    }

    @Test
    void 모임_관리자가_아니면_종료_테스트를_실패한다() {
        Group group = Group.builder()
            .manager(manager)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.endGroupById(participant, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }
}
