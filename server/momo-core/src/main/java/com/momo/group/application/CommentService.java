package com.momo.group.application;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.application.dto.request.CommentCreateRequest;
import com.momo.group.application.dto.request.CommentsRequest;
import com.momo.group.application.dto.response.CommentResponse;
import com.momo.group.application.dto.response.CommentsResponse;
import com.momo.group.domain.Group;
import com.momo.group.domain.post.Post;
import com.momo.group.domain.post.comment.Comment;
import com.momo.group.domain.repository.CommentRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.group.domain.repository.PostRepository;
import com.momo.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final ParticipantRepository participantRepository;
    private final CommentRepository commentRepository;

    public CommentResponse createComment(User user, CommentCreateRequest request) {
        Post post = getPostById(request.getPostId());
        validateParticipant(post.getGroup(), user);
        Comment comment = Comment.create(post, user, request.getContents());
        return CommentResponse.of(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public CommentsResponse findPageByPostId(User user, CommentsRequest request) {
        Post post = getPostById(request.getPostId());
        validateParticipant(post.getGroup(), user);

        long commentCnt = commentRepository.countByPost(post);
        List<Comment> comments = commentRepository
            .findAllByPostOrderByIdDesc(post, request.getLastCommentId(), request.getSize());

        return CommentsResponse.of(comments, commentCnt);
    }

    public void deleteCommentById(Long commentId, User loginUser) {
        Comment comment = getCommentById(commentId);
        comment.validateWriter(loginUser);
        commentRepository.delete(comment);
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    private void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
