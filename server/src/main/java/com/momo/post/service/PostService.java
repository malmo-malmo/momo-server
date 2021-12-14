package com.momo.post.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.post.controller.dto.PostCardResponse;
import com.momo.post.controller.dto.PostCardsRequest;
import com.momo.post.controller.dto.PostCreateRequest;
import com.momo.post.controller.dto.PostResponse;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostType;
import com.momo.post.domain.repository.PostRepository;
import com.momo.user.domain.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    public Long create(User user, PostCreateRequest request) {
        Groups group = getGroupById(request.getGroupId());
        validatePostType(group, user, request.getTypeName());
        Post post = Post.create(user, group, request.toEntity());
        post.changeImages(request.getImageUrls());
        return postRepository.save(post).getId();
    }

    public void validatePostType(Groups group, User user, String typeName) {
        if (PostType.NOTICE.isSameName(typeName)) {
            validateGroupManager(group, user);
        }
        if (PostType.NORMAL.isSameName(typeName)) {
            validateParticipant(group, user);
        }
    }

    public void validateGroupManager(Groups group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    @Transactional(readOnly = true)
    public PostResponse findById(User user, Long postId) {
        Post post = postRepository.findPostAndAuthorById(postId);
        validateParticipant(post.getGroup(), user);
        return PostResponse.of(post);
    }

    @Transactional(readOnly = true)
    public List<PostCardResponse> findPageByGroupIdAndType(User user, PostCardsRequest request) {
        Groups group = getGroupById(request.getGroupId());
        validateParticipant(group, user);
        PostType postType = PostType.of(request.getType());
        PageRequest page = PageRequest.of(request.getPage(), request.getSize());
        return postRepository.findAllByGroupAndTypeOrderByCreatedDateDesc(group, postType, page);
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateParticipant(Groups group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
