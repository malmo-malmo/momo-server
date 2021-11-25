package com.momo.post.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.post.controller.dto.CommentCreateRequest;
import com.momo.post.domain.model.Comment;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.repository.CommentRepository;
import com.momo.post.domain.repository.PostRepository;
import com.momo.user.domain.model.User;
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

    public void create(User user, CommentCreateRequest commentCreateRequest) {
        Post post = getPostById(commentCreateRequest.getPostId());
        validateIsGroupParticipant(user, post.getGroup());
        Comment comment = Comment.create(post, user, commentCreateRequest.getContents());
        commentRepository.save(comment);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateIsGroupParticipant(User user, Groups group) {
        if (!participantRepository.existsByUserAndGroup(user, group)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
