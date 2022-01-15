package com.momo.domain.post.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.image.service.S3UploadService;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.user.entity.User;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private PostService postService;

    private User manager;

    private User participant;

    private User user;

    private Group group;

    @BeforeEach
    void setUp() {
        manager = User.builder().id(1L).build();
        participant = User.builder().id(2L).build();
        user = User.builder().id(3L).build();
        group = Group.builder()
            .id(1L)
            .manager(manager)
            .build();
    }

    @Test
    void 모임_관리자는_일반_게시물_등록_테스트를_성공한다() throws IOException {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
            .typeName(PostType.NORMAL.getCode())
            .build();
        Post post = Post.builder().id(1L).build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(postRepository.save(any())).willReturn(post);

        Long actual = postService.create(manager, postCreateRequest);

        verify(groupRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(postRepository).save(any());
        assertThat(actual).isEqualTo(post.getId());
    }

    @Test
    void 모임_참여자는_일반_게시물_등록_테스트를_성공한다() throws IOException {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
            .typeName(PostType.NORMAL.getCode())
            .build();
        Post post = Post.builder().id(1L).build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(postRepository.save(any())).willReturn(post);

        Long actual = postService.create(participant, postCreateRequest);

        verify(groupRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(postRepository).save(any());
        assertThat(actual).isEqualTo(post.getId());
    }

    @Test
    void 모임_참여자가_아니면_일반_게시물_등록_테스트를_실패한다() {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
            .typeName(PostType.NORMAL.getCode())
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> postService.create(participant, postCreateRequest))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 모임_관리자는_공지사항_등록_테스트를_성공한다() throws IOException {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
            .typeName(PostType.NOTICE.getCode())
            .build();
        Post post = Post.builder().id(1L).build();

        given(groupRepository.findById(any())).willReturn(of(group));
        given(postRepository.save(any())).willReturn(post);

        Long actual = postService.create(manager, postCreateRequest);

        verify(groupRepository).findById(any());
        verify(participantRepository, never()).existsByGroupAndUser(any(), any());
        verify(postRepository).save(any());
        assertThat(actual).isEqualTo(post.getId());
    }

    @Test
    void 모임_참여자는_공지사항_등록_테스트를_실패한다() {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
            .typeName(PostType.NOTICE.getCode())
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> postService.create(participant, postCreateRequest))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 모임에_관련없는_유저는_공지사항_등록_테스트를_실패한다() {
        PostCreateRequest postCreateRequest = PostCreateRequest.builder()
            .typeName(PostType.NOTICE.getCode())
            .build();

        given(groupRepository.findById(any())).willReturn(of(group));

        assertThatThrownBy(() -> postService.create(user, postCreateRequest))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_MANAGER_AUTHORIZED.getMessage());
    }

    @Test
    void 게시물_상세조회_테스트를_성공한다() {
        Post post = Post.builder()
            .id(1L)
            .author(manager)
            .group(group)
            .build();

        given(postRepository.findPostAndAuthorById(any())).willReturn(post);
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);

        PostResponse actual = postService.findById(manager, 1L);

        verify(postRepository).findPostAndAuthorById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getAuthorId()).isEqualTo(manager.getId()),
            () -> assertThat(actual.getAuthorImage()).isEqualTo(manager.getImageUrl()),
            () -> assertThat(actual.getAuthorNickname()).isEqualTo(manager.getNickname()),
            () -> assertThat(actual.getTitle()).isEqualTo(post.getTitle()),
            () -> assertThat(actual.getContents()).isEqualTo(post.getContents()),
            () -> assertThat(actual.getImageUrls().size()).isEqualTo(0)
        );
    }

    @Test
    void 모임_참여자가_아니면_게시물_상세조회_테스트를_실패한다() {
        Post post = Post.builder()
            .id(1L)
            .author(manager)
            .group(group)
            .build();

        given(postRepository.findPostAndAuthorById(any())).willReturn(post);
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> postService.findById(manager, 1L))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }
}
