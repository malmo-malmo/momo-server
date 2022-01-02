package com.momo.domain.post.service;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Groups;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.repository.CommentRepository;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.dto.CommentsRequest;
import com.momo.domain.post.dto.CommentsResponse;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DisplayName("댓글 서비스 테스트")
public class CommentServiceTest extends ServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private Post post;

    private User author;

    @BeforeEach
    void setUp() {
        Groups group = Groups.builder().id(1L).build();
        post = Post.builder()
            .id(1L)
            .group(group)
            .build();
        author = User.builder()
            .id(1L)
            .nickname("닉네임")
            .build();
    }

    @Test
    void 댓글_등록_테스트() {
        CommentCreateRequest commentCreateRequest = CommentCreateRequest.builder()
            .postId(1L)
            .contents("댓글 내용")
            .build();
        Comment comment = Comment.create(post, author, commentCreateRequest.getContents());

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(commentRepository.save(any())).willReturn(comment);

        CommentResponse actual = commentService.create(author, commentCreateRequest);

        verify(postRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(commentRepository).save(any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getAuthorId()).isEqualTo(comment.getUser().getId()),
            () -> assertThat(actual.getAuthorImage()).isEqualTo(comment.getUser().getImageUrl()),
            () -> assertThat(actual.getAuthorNickname()).isEqualTo(comment.getUser().getNickname()),
            () -> assertThat(actual.getContents()).isEqualTo(comment.getContents())
        );
    }

    @Test
    void 모임_참여자가_아니면_댓글_등록_테스트를_실패한다() {
        CommentCreateRequest commentCreateRequest = CommentCreateRequest.builder()
            .postId(1L)
            .contents("댓글 내용")
            .build();

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> commentService.create(author, commentCreateRequest))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 댓글_목록_조회_테스트() {
        CommentsRequest commentsRequest = CommentsRequest.builder()
            .postId(1L)
            .page(0)
            .size(10)
            .build();
        List<Comment> comments = List.of(
            Comment.create(post, author, "댓글 내용1"),
            Comment.create(post, author, "댓글 내용2")
        );
        Pageable pageable = PageRequest.of(0, 10);
        long total = 100L;

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(commentRepository.findAllByPostOrderByCreatedDateAsc(any(), any())).willReturn(
            new PageImpl<>(comments, pageable, total)
        );

        CommentsResponse actual = commentService.findPageByPostId(author, commentsRequest);

        verify(postRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(commentRepository).findAllByPostOrderByCreatedDateAsc(any(), any());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getCommentCnt()).isEqualTo(total),
            () -> assertThat(actual.getCommentResponses().size()).isEqualTo(comments.size()),
            () -> assertThat(actual.getCommentResponses().get(0).getAuthorId())
                .isEqualTo(comments.get(0).getUser().getId()),
            () -> assertThat(actual.getCommentResponses().get(0).getAuthorImage())
                .isEqualTo(comments.get(0).getUser().getImageUrl()),
            () -> assertThat(actual.getCommentResponses().get(0).getAuthorNickname())
                .isEqualTo(comments.get(0).getUser().getNickname()),
            () -> assertThat(actual.getCommentResponses().get(0).getContents())
                .isEqualTo(comments.get(0).getContents()),
            () -> assertThat(actual.getCommentResponses().get(1).getAuthorId())
                .isEqualTo(comments.get(1).getUser().getId()),
            () -> assertThat(actual.getCommentResponses().get(1).getAuthorImage())
                .isEqualTo(comments.get(1).getUser().getImageUrl()),
            () -> assertThat(actual.getCommentResponses().get(1).getAuthorNickname())
                .isEqualTo(comments.get(1).getUser().getNickname()),
            () -> assertThat(actual.getCommentResponses().get(1).getContents())
                .isEqualTo(comments.get(1).getContents())
        );
    }

    @Test
    void 모임_참여자가_아니면_댓글_목록_조회_테스트를_실패한다() {
        CommentsRequest commentsRequest = CommentsRequest.builder()
            .postId(1L)
            .page(0)
            .size(10)
            .build();

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> commentService.findPageByPostId(author, commentsRequest))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }
}
