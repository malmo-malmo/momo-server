package com.momo.domain.post.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;

    private final ParticipantRepository participantRepository;

    private final CommentRepository commentRepository;

    public CommentResponse create(User user, CommentCreateRequest commentCreateRequest) {
        Post post = getPostById(commentCreateRequest.getPostId());
        validateParticipant(post.getGroup(), user);
        Comment comment = Comment.create(post, user, commentCreateRequest.getContents());
        return CommentResponse.of(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public CommentsResponse findPageByPostId(User user, CommentsRequest request) {
        Post post = getPostById(request.getPostId());
        validateParticipant(post.getGroup(), user);
        Page<Comment> pageComments = commentRepository
            .findAllByPostOrderByCreatedDateAsc(post, PageRequest.of(request.getPage(), request.getSize()));
        return CommentsResponse.of(pageComments.getContent(), pageComments.getTotalElements());
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
