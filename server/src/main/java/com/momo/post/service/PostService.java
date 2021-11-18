package com.momo.post.service;

import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.group.domain.model.Groups;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.group.domain.repository.ParticipantRepository;
import com.momo.post.controller.dto.PostCreateRequest;
import com.momo.post.domain.model.Post;
import com.momo.post.domain.model.PostImage;
import com.momo.post.domain.model.PostType;
import com.momo.post.domain.repository.PostImageRepository;
import com.momo.post.domain.repository.PostRepository;
import com.momo.user.domain.model.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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

    private final PostImageRepository postImageRepository;

    public Long create(User user, PostCreateRequest request) {
        Groups group = getGroupById(request.getGroupId());

        //TODO : 리팩토링 하기!
        if (request.getPostType().equals(PostType.NORMAL.name())) {
            validateIsParticipant(user, group);
        } else {
            group.validateIsManager(user);
        }

        Post post = postRepository.save(Post.create(user, group, request.toEntity()));

        if (!CollectionUtils.isEmpty(request.getImageUrls())) {
            List<PostImage> postImages = request.getImageUrls().stream()
                .map(imageUrl -> PostImage.create(post, imageUrl)).collect(Collectors.toList());
            postImageRepository.saveAll(postImages);
        }

        return post.getId();
    }

    public Groups getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_GROUP_ID));
    }

    public void validateIsParticipant(User user, Groups group) {
        if (!participantRepository.existsByUserAndGroup(user, group)) {
            throw new CustomException(ErrorCode.GROUP_PARTICIPANT_UNAUTHORIZED);
        }
    }
}
