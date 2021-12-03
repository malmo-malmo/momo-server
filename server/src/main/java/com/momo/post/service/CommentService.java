package com.momo.post.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.post.controller.dto.CommentCreateRequest;
import com.momo.post.controller.dto.CommentResponse;
import com.momo.post.controller.dto.CommentsRequest;
import com.momo.post.controller.dto.CommentsResponse;
import com.momo.post.domain.model.Comment;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.repository.CommentRepository;
import com.momo.post.domain.repository.PostRepository;
import com.momo.user.domain.model.User;
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
        validateGroupParticipant(user, post.getGroup());
        Comment comment = Comment.create(post, user, commentCreateRequest.getContents());
        return CommentResponse.of(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public CommentsResponse findPageByPostId(User user, CommentsRequest request) {
        Post post = getPostById(request.getPostId());
        validateGroupParticipant(user, post.getGroup());
        Page<Comment> pageComments = commentRepository
            .findAllByPostOrderByCreatedDateAsc(post, PageRequest.of(request.getPage(), request.getSize()));
        return CommentsResponse.of(pageComments.getContent(), pageComments.getTotalElements());
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateGroupParticipant(User user, Groups group) {
        if (!participantRepository.existsByUserAndGroup(user, group)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
