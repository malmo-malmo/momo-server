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
import com.momo.post.domain.model.Image;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostType;
import com.momo.post.domain.repository.ImageRepository;
import com.momo.post.domain.repository.PostRepository;
import com.momo.user.domain.model.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final GroupRepository groupRepository;

    private final ParticipantRepository participantRepository;

    private final ImageRepository imageRepository;

    public Long create(User user, PostCreateRequest request) {
        Groups group = getGroupById(request.getGroupId());
        //TODO : 리팩토링 하기!
        if (request.getPostType().equals(PostType.NORMAL.name())) {
            validateGroupParticipant(user, group);
        } else {
            if (group.isNotManager(user)) {
                throw new CustomException(ErrorCode.GROUP_NOTICE_UNAUTHORIZED);
            }
        }
        Post post = postRepository.save(Post.create(user, group, request.toEntity()));

        if (!CollectionUtils.isEmpty(request.getImageUrls())) {
            List<Image> postImages = request.getImageUrls().stream()
                .map(imageUrl -> Image.create(post, imageUrl)).collect(Collectors.toList());
            imageRepository.saveAll(postImages);
        }

        return post.getId();
    }

    @Transactional(readOnly = true)
    public PostResponse find(User user, Long postId) {
        Post post = postRepository.findPostAndAuthorById(postId);
        validateGroupParticipant(user, post.getGroup());
        return PostResponse.of(post, imageRepository.findAllByPost(post));
    }

    @Transactional(readOnly = true)
    public List<PostCardResponse> findPageByGroupAndType(User user, PostCardsRequest request) {
        Groups group = getGroupById(request.getGroupId());
        validateGroupParticipant(user, group);
        PostType postType = PostType.of(request.getType());
        PageRequest page = PageRequest.of(request.getPage(), request.getSize());
        return postRepository.findAllByGroupAndTypeOrderByCreatedDateDesc(group, postType, page);
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }

    public void validateGroupParticipant(User user, Groups group) {
        if (!participantRepository.existsByUserAndGroup(user, group)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
