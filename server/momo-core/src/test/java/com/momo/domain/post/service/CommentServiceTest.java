package com.momo.domain.post.service;

import static com.momo.CommentFixture.getComment;
import static com.momo.CommentFixture.getCommentCreateRequest;
import static com.momo.CommentFixture.getCommentWithId;
import static com.momo.CommentFixture.getCommentsRequest;
import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.PostFixture.getPostWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.post.entity.PostType.NORMAL;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.momo.common.ServiceTest;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.entity.Group;
import com.momo.group.repository.ParticipantRepository;
import com.momo.post.dto.CommentCreateRequest;
import com.momo.post.dto.CommentResponse;
import com.momo.post.dto.CommentsRequest;
import com.momo.post.dto.CommentsResponse;
import com.momo.post.entity.Comment;
import com.momo.post.entity.Post;
import com.momo.post.repository.CommentRepository;
import com.momo.post.repository.PostRepository;
import com.momo.post.service.CommentService;
import com.momo.post.service.impl.CommentServiceImpl;
import com.momo.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@DisplayName("댓글 서비스 테스트")
public class CommentServiceTest extends ServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private CommentRepository commentRepository;

    private CommentService commentService;

    private Post post;
    private User author;

    @BeforeEach
    void setUp() {
        commentService = new CommentServiceImpl(postRepository, participantRepository, commentRepository);
        author = getUserWithId();
        Group group = getGroupWithId(author);
        post = getPostWithId(author, group, NORMAL);
    }

    @Test
    void 댓글_등록_테스트() {
        CommentCreateRequest request = getCommentCreateRequest(post.getId());
        Comment comment = getComment(post, author);

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(commentRepository.save(any())).willReturn(comment);

        CommentResponse actual = commentService.createComment(author, request);

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
        CommentCreateRequest request = getCommentCreateRequest(post.getId());

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> commentService.createComment(author, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 댓글_목록_조회_테스트() {
        CommentsRequest request = getCommentsRequest(post.getId());
        List<Comment> comments = List.of(getComment(post, author), getComment(post, author));
        long commentCnt = 2L;

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(true);
        given(commentRepository.countByPost(any())).willReturn(commentCnt);
        given(commentRepository.findAllByPostOrderByIdDesc(any(), any(), anyInt())).willReturn(comments);

        CommentsResponse actual = commentService.findPageByPostId(author, request);

        verify(postRepository).findById(any());
        verify(participantRepository).existsByGroupAndUser(any(), any());
        verify(commentRepository).countByPost(any());
        verify(commentRepository).findAllByPostOrderByIdDesc(any(), any(), anyInt());
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getCommentCnt()).isEqualTo(commentCnt),
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
        CommentsRequest request = getCommentsRequest(post.getId());

        given(postRepository.findById(any())).willReturn(of(post));
        given(participantRepository.existsByGroupAndUser(any(), any())).willReturn(false);

        assertThatThrownBy(() -> commentService.findPageByPostId(author, request))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED.getMessage());
    }

    @Test
    void 댓글_삭제_테스트에_성공한다() {
        Comment comment = getCommentWithId(post, author);
        given(commentRepository.findById(anyLong())).willReturn(Optional.of(comment));
        commentService.deleteCommentById(comment.getId(), author);
        verify(commentRepository).delete(comment);
    }

    @Test
    void 댓글_작성자가_아니면_댓글_삭제에_실패한다() {
        Comment comment = getComment(post, author);

        assertThatThrownBy(() -> commentService.deleteCommentById(comment.getId(), getUserWithId()))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorCode.INVALID_INDEX_NUMBER.getMessage());
    }
}
