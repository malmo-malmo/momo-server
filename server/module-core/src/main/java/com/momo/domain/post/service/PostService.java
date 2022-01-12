package com.momo.domain.post.service;

import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.image.service.ImageUploadService;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.PostCardsRequest;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.user.entity.User;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    private final ImageUploadService imageUploadService;

    public Long create(User user, PostCreateRequest request) throws IOException {
        Group group = getGroupById(request.getGroupId());
        validatePostType(group, user, request.getTypeName());
        Post post = Post.create(user, group, request.toEntity());
        List<String> imageUrls = imageUploadService.uploadAll(request.getImages(), "post");
        post.updateImages(imageUrls);
        return postRepository.save(post).getId();
    }

    public void validatePostType(Group group, User user, String typeName) {
        if (PostType.NOTICE.isSameName(typeName)) {
            validateGroupManager(group, user);
        }
        if (PostType.NORMAL.isSameName(typeName)) {
            validateParticipant(group, user);
        }
    }

    public void validateGroupManager(Group group, User user) {
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
        Group group = getGroupById(request.getGroupId());
        validateParticipant(group, user);
        PostType postType = PostType.of(request.getType());
        PageRequest page = PageRequest.of(request.getPage(), request.getSize());
        return postRepository.findAllByGroupAndTypeOrderByCreatedDateDesc(group, postType, page);
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
