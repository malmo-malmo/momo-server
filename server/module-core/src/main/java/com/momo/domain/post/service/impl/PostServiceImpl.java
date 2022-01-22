package com.momo.domain.post.service.impl;

import com.momo.domain.aws.service.ImageUploadService;
import com.momo.domain.aws.util.GenerateUploadPathUtil;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.repository.GroupRepository;
import com.momo.domain.group.repository.ParticipantRepository;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.PostCardsRequest;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostModifyRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.post.service.PostService;
import com.momo.domain.user.entity.User;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    private final ImageUploadService imageUploadService;

    public Long create(User user, PostCreateRequest request) throws IOException {
        Long groupId = request.getGroupId();

        Group group = getGroupById(groupId);
        validatePostType(group, user, request.getTypeName());

        Post post = postRepository.save(Post.create(user, group, request.toEntity()));
        Long postId = post.getId();

        List<String> imageUrls = imageUploadService
            .uploadAll(request.getImages(), GenerateUploadPathUtil.getPostImage(groupId, postId));
        post.updateImages(imageUrls);
        return postId;
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

    public void updatePost(PostModifyRequest request, User user) {
        Post post = postRepository.findById(request.getPostId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
        validatePost(post.getAuthor(), user);
        post.updateTitleAndContents(request.getTitle(), request.getContent());
    }

    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
        validatePost(post.getAuthor(), user);
        postRepository.delete(post);
    }

    public void validatePost(User postUser, User updateUser) {
        boolean isNotWritingPost = !postUser.getId().equals(updateUser.getId());
        if (isNotWritingPost) {
            throw new CustomException(ErrorCode.POST_CONTROL_UNAUTHORIZED);
        }
    }

    public void validateParticipant(Group group, User user) {
        if (!participantRepository.existsByGroupAndUser(group, user)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }

    public void validateGroupManager(Group group, User user) {
        if (!group.isManager(user)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    public void validatePostType(Group group, User user, String typeName) {
        if (PostType.NOTICE.isSameName(typeName)) {
            validateGroupManager(group, user);
        }
        if (PostType.NORMAL.isSameName(typeName)) {
            validateParticipant(group, user);
        }
    }

    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
