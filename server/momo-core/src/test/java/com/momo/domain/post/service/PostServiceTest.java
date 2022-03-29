package com.momo.domain.post.service;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.PostFixture.getPostCreateRequest;
import static com.momo.PostFixture.getPostUpdateRequest;
import static com.momo.PostFixture.getPostWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.post.entity.PostType.NORMAL;
import static com.momo.post.entity.PostType.NOTICE;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.aws.service.S3UploadService;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.entity.Group;
import com.momo.group.repository.GroupRepository;
import com.momo.group.repository.ParticipantRepository;
import com.momo.post.dto.PostCreateRequest;
import com.momo.post.dto.PostResponse;
import com.momo.post.dto.PostUpdateRequest;
import com.momo.post.entity.Post;
import com.momo.post.repository.PostRepository;
import com.momo.post.service.PostService;
import com.momo.post.service.impl.PostServiceImpl;
import com.momo.user.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("게시물 서비스 테스트")
public class PostServiceTest extends ServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private S3UploadService s3UploadService;

    private PostService postService;

    private User manager;
    private User participant;
    private User user;
    private Group group;

    @BeforeEach
    void setUp() {
        postService = new PostServiceImpl(postRepository, groupRepository, participantRepository, s3UploadService);
        manager = getUserWithId();
        participant = getUserWithId();
        user = getUserWithId();
        group = getGroupWithId(manager);
    }

    @Test
    void 모임_관리자는_일반_게시물_등록_테스트를_성공한다() {
        PostCreateRequest request = getPostCreateRequest(group.getId(), NORMAL);
        Post post = getPostWithId(manager, group, NORMAL);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(postRepository.save(any())).willReturn(post);

        PostResponse response = postService.create(manager, request);

        verify(groupRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(postRepository).save(any());
        verifyPostResponse(response, post);
    }

    @Test
    void 모임_참여자는_일반_게시물_등록_테스트를_성공한다() {
        PostCreateRequest request = getPostCreateRequest(group.getId(), NORMAL);
        Post post = getPostWithId(manager, group, NORMAL);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(postRepository.save(any())).willReturn(post);

        PostResponse response = postService.create(participant, request);

        verify(groupRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(postRepository).save(any());
        verifyPostResponse(response, post);
    }

    @Test
    void 모임_참여자가_아니면_일반_게시물_등록_테스트를_실패한다() {
        PostCreateRequest request = getPostCreateRequest(group.getId(), NORMAL);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> postService.create(participant, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 모임_관리자는_공지사항_등록_테스트를_성공한다() {
        PostCreateRequest request = getPostCreateRequest(group.getId(), NOTICE);
        Post post = getPostWithId(manager, group, NOTICE);

        given(groupRepository.findById(any())).willReturn(of(group));
        given(postRepository.save(any())).willReturn(post);

        PostResponse response = postService.create(manager, request);

        verify(groupRepository).findById(any());
        verify(participantRepository, never()).existsByGroupAndUser(any(), any());
        verify(postRepository).save(any());
        verifyPostResponse(response, post);
    }

    @Test
    void 모임_참여자는_공지사항_등록_테스트를_실패한다() {
        PostCreateRequest request = getPostCreateRequest(group.getId(), NOTICE);
        given(groupRepository.findById(any())).willReturn(of(group));
        verifyGroupManagerAuthorized(request);
    }

    @Test
    void 모임에_관련없는_유저는_공지사항_등록_테스트를_실패한다() {
        PostCreateRequest request = getPostCreateRequest(group.getId(), NOTICE);
        given(groupRepository.findById(any())).willReturn(of(group));
        verifyGroupManagerAuthorized(request);
    }

    @Test
    void 게시물_상세조회_테스트를_성공한다() {
        Post post = getPostWithId(manager, group, NOTICE);

        given(postRepository.findPostAndAuthorById(any())).willReturn(post);
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);

        PostResponse response = postService.findById(manager, 1L);

        verify(postRepository).findPostAndAuthorById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verifyPostResponse(response, post);
    }

    @Test
    void 모임_참여자가_아니면_게시물_상세조회_테스트를_실패한다() {
        Post post = getPostWithId(manager, group, NOTICE);

        given(postRepository.findPostAndAuthorById(any())).willReturn(post);
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> postService.findById(manager, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 게시물_수정_테스트를_성공한다() {
        Post post = getPostWithId(manager, group, NOTICE);
        PostUpdateRequest request = getPostUpdateRequest(post.getId());

        given(postRepository.findById(request.getPostId())).willReturn(Optional.of(post));
        postService.updatePost(request, manager);

        Assertions.assertAll(
            () -> assertThat(post.getTitle()).isEqualTo(request.getTitle()),
            () -> assertThat(post.getContents()).isEqualTo(request.getContents())
        );
    }

    @Test
    void 게시물_작성자가_아니면_게시물_수정_테스트에_실패한다() {
        Post post = getPostWithId(manager, group, NOTICE);
        PostUpdateRequest request = getPostUpdateRequest(post.getId());

        given(postRepository.findById(request.getPostId())).willReturn(Optional.of(post));

        assertThatThrownBy(() -> postService.updatePost(request, user))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.POST_CONTROL_UNAUTHORIZED.getMessage());
    }

    @Test
    void 게시물_삭제_테스트에_성공한다() {
        Post post = getPostWithId(manager, group, NOTICE);
        given(postRepository.findById(post.getId())).willReturn(Optional.of(post));
        postService.deletePost(post.getId(), manager);
        verify(postRepository).delete(post);
    }

    @Test
    void 게시물_작성자가_아니면_삭제_테스트에_실패한다() {
        Post post = getPostWithId(manager, group, NOTICE);

        given(postRepository.findById(post.getId())).willReturn(Optional.of(post));

        assertThatThrownBy(() -> postService.deletePost(post.getId(), user))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.POST_CONTROL_UNAUTHORIZED.getMessage());
    }

    private void verifyPostResponse(PostResponse response, Post post) {
        Assertions.assertAll(
            () -> assertThat(response).isNotNull(),
            () -> assertThat(response.getId()).isEqualTo(post.getId()),
            () -> assertThat(response.getAuthorId()).isEqualTo(manager.getId()),
            () -> assertThat(response.getAuthorImage()).isEqualTo(manager.getImageUrl()),
            () -> assertThat(response.getAuthorNickname()).isEqualTo(manager.getNickname()),
            () -> assertThat(response.getTitle()).isEqualTo(post.getTitle()),
            () -> assertThat(response.getContents()).isEqualTo(post.getContents()),
            () -> assertThat(response.getImageUrls().size()).isEqualTo(0)
        );
    }

    private void verifyGroupManagerAuthorized(PostCreateRequest request) {
        assertThatThrownBy(() -> postService.create(user, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }
}
