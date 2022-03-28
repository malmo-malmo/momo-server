package com.momo.domain.post.service.impl;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.post.dto.CommentCreateRequest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.dto.CommentsRequest;
import com.momo.domain.post.dto.CommentsResponse;
import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.repository.CommentRepository;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.post.service.CommentService;
import com.momo.domain.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

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
