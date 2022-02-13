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
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.dto.PostUpdateRequest;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.post.repository.PostRepository;
import com.momo.domain.post.service.PostService;
import com.momo.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;
    private final ImageUploadService imageUploadService;

    public PostResponse create(User loginUser, PostCreateRequest request) {
        Group group = getGroupById(request.getGroupId());
        validatePostType(group, loginUser, request.getPostType());
        Post post = postRepository.save(Post.create(loginUser, group, request.toEntity()));
        uploadImages(request.getImages(), post);
        return PostResponse.of(post);
    }

    @Transactional(readOnly = true)
    public PostResponse findById(User loginUser, Long postId) {
        Post post = postRepository.findPostAndAuthorById(postId);
        validateParticipant(post.getGroup(), loginUser);
        return PostResponse.of(post);
    }

    @Transactional(readOnly = true)
    public List<PostCardResponse> findPageByGroupIdAndType(User loginUser, PostCardsRequest request) {
        Group group = getGroupById(request.getGroupId());
        validateParticipant(group, loginUser);
        PageRequest page = PageRequest.of(request.getPage(), request.getSize());
        return postRepository
            .findAllWithAuthorByGroupAndTypeOrderByCreatedDateDesc(group, request.getPostType(), page);
    }

    public void updatePost(PostUpdateRequest request, User loginUser) {
        Post post = postRepository.findById(request.getPostId())
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
        validatePost(post, loginUser);
        post.updateTitleAndContents(request.getTitle(), request.getContents());
        uploadImages(request.getImages(), post);
    }

    private void uploadImages(List<MultipartFile> images, Post post) {
        List<String> imageUrls = imageUploadService
            .uploadAll(images, GenerateUploadPathUtil.getPostImage(post.getGroup().getId(), post.getId()));
        post.updateImages(imageUrls);
    }

    public void deletePost(Long postId, User loginUser) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
        validatePost(post, loginUser);
        postRepository.delete(post);
    }

    private void validatePost(Post post, User loginUser) {
        if (!post.isAuthor(loginUser)) {
            throw new CustomException(ErrorCode.POST_CONTROL_UNAUTHORIZED);
        }
    }

    private void validateParticipant(Group group, User loginUser) {
        if (!participantRepository.existsByGroupAndUser(group, loginUser)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }

    private void validateGroupManager(Group group, User loginUser) {
        if (!group.isManager(loginUser)) {
            throw new CustomException(ErrorCode.GROUP_MANAGER_AUTHORIZED);
        }
    }

    private void validatePostType(Group group, User loginUser, PostType postType) {
        if (PostType.NOTICE.equals(postType)) {
            validateGroupManager(group, loginUser);
            return;
        }
        if (PostType.NORMAL.equals(postType)) {
            validateParticipant(group, loginUser);
        }
    }

    private Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}
