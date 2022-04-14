package com.momo.unit.group.application;

import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.GroupFixture.getGroupResponse;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.district.entity.City.SEOUL;
import static com.momo.group.domain.category.Category.EMPLOYMENT;
import static com.momo.group.domain.category.Category.STOCK;
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
import com.momo.group.application.dto.request.GroupUpdateRequest;
import com.momo.group.application.dto.response.GroupCreateResponse;
import com.momo.group.application.dto.response.GroupImageUpdateResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.participant.Participant;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.user.domain.User;
import com.momo.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;

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
    @DisplayName("모임을 생성한다")
    void createGroup_Success() {
        GroupCreateRequest groupCreateRequest = getGroupCreateRequest(EMPLOYMENT, manager.getUniversity());
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
    @DisplayName("모임 관리자가 모임 정보를 수정한다")
    void updateGroupInformation_Manager_Success() {
        Group group = getGroupWithId(manager);
        GroupUpdateRequest request = GroupUpdateRequest.builder()
            .id(1L)
            .name("운동 모임")
            .category(STOCK)
            .university(manager.getUniversity())
            .city(SEOUL)
            .district("강동구")
            .recruitmentCnt(10)
            .introduction("모임 설명")
            .isOffline(true)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        groupService.updateGroupInformation(manager, request);

        verify(groupRepository).findById(any());
        Assertions.assertAll(
            () -> assertThat(group.getName()).isEqualTo(request.getName()),
            () -> assertThat(group.getCategory()).isEqualTo(request.getCategory()),
            () -> assertThat(group.getUniversity()).isEqualTo(request.getUniversity()),
            () -> assertThat(group.getLocation().getCity()).isEqualTo(request.getCity()),
            () -> assertThat(group.getLocation().getDistrict()).isEqualTo(request.getDistrict()),
            () -> assertThat(group.getRecruitmentCnt()).isEqualTo(request.getRecruitmentCnt()),
            () -> assertThat(group.getIntroduction()).isEqualTo(request.getIntroduction()),
            () -> assertThat(group.isOffline()).isEqualTo(request.getIsOffline())
        );
    }

    @Test
    @DisplayName("모임 관리자가 아니면 모임 정보를 수정을 실패한다")
    void updateGroupInformation_NotManager_Failure() {
        Group group = getGroupWithId(manager);
        GroupUpdateRequest request = GroupUpdateRequest.builder().build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.updateGroupInformation(participant, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("모임 정원이 이전에 설정한 값보다 작으면 모임 정보 수정을 실패한다")
    void updateGroupInformation_LessThanPrevRecruitmentCnt_Success() {
        Group group = getGroupWithId(manager);
        GroupUpdateRequest request = GroupUpdateRequest.builder()
            .recruitmentCnt(9)
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.updateGroupInformation(manager, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.INVALID_GROUP_RECRUITMENT_COUNT.getMessage());
    }

    @Test
    @DisplayName("모임 관리자가 모임 대표 이미지를 변경한다")
    void updateGroupImage_Manager_Success() {
        String imageUrl = "imageUrl";
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(s3UploadService.upload(any(), any())).willReturn(imageUrl);

        GroupImageUpdateResponse actual = groupService
            .updateGroupImage(manager, 1L, new MockMultipartFile("image", "image".getBytes()));

        assertThat(actual.getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    @DisplayName("모임 관리자가 아닌 유저가 모임 대표 이미지를 변경하면 실패한다")
    void updateGroupImage_NotManager_Failure() {
        Group group = getGroupWithId(manager);
        MockMultipartFile imageFile = new MockMultipartFile("image", "image".getBytes());

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.updateGroupImage(participant, 1L, imageFile))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("모임 관리자가 관리자를 변경한다")
    void updateManager_Manager_Success() {
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
    @DisplayName("모임 관리자가 아니면 관리자 변경을 실패한다")
    void updateManager_NotManager_Failure() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.updateManager(participant, 1L, 2L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("모임 관리자가 해당 모임의 참여자가 아닌 유저에게 권한을 주면 실패한다")
    void updateManager_NotParticipant_Failure() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(userRepository.findById(any())).willReturn(of(participant));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> groupService.updateManager(manager, 1L, 2L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    @DisplayName("모임 관리자가 모임을 종료한다")
    void endGroup_Manager_Success() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        groupService.endGroup(manager, 1L);

        verify(groupRepository).findById(any());
        assertThat(group.isEnd()).isTrue();
    }

    @Test
    @DisplayName("모임 관리자가 아니면 모임 종료를 실패한다")
    void endGroup_NotManager_Failure() {
        Group group = getGroupWithId(manager);

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> groupService.endGroup(participant, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }
}
