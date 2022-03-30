package com.momo;

import static com.momo.common.FixtureComponents.AUTHOR_IMAGE;
import static com.momo.common.FixtureComponents.AUTHOR_NICKNAME;
import static com.momo.common.FixtureComponents.CONTENTS;
import static com.momo.common.FixtureComponents.DATE_TIME;
import static com.momo.common.FixtureComponents.IMAGE;
import static com.momo.common.FixtureComponents.IMAGES;
import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;
import static com.momo.common.FixtureComponents.PAGE_SIZE;
import static com.momo.common.FixtureComponents.TITLE;

import com.momo.group.entity.Group;
import com.momo.post.dto.PostCardResponse;
import com.momo.post.dto.PostCardsRequest;
import com.momo.post.dto.PostCreateRequest;
import com.momo.post.dto.PostResponse;
import com.momo.post.dto.PostUpdateRequest;
import com.momo.post.entity.Post;
import com.momo.post.entity.PostType;
import com.momo.user.domain.model.User;
import java.util.List;

public class PostFixture {

    public static Post getPost(User author, Group group, PostType postType) {
        INCREASE_ID++;
        return Post.builder()
            .author(author)
            .group(group)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .type(postType)
            .build();
    }

    public static Post getPostWithId(User author, Group group, PostType postType) {
        INCREASE_ID++;
        return Post.builder()
            .id(INCREASE_ID)
            .author(author)
            .group(group)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .type(postType)
            .build();
    }

    public static PostCreateRequest getPostCreateRequest(Long groupId, PostType postType) {
        INCREASE_ID++;
        return PostCreateRequest.builder()
            .groupId(groupId)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .postType(postType)
            .images(List.of(IMAGE))
            .build();
    }

    public static PostUpdateRequest getPostUpdateRequest(Long postId) {
        INCREASE_ID++;
        return PostUpdateRequest.builder()
            .postId(postId)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .images(IMAGES)
            .build();
    }

    public static PostCardsRequest getPostCardsRequest(Long groupId, PostType postType) {
        return PostCardsRequest.builder()
            .groupId(groupId)
            .postType(postType)
            .size(PAGE_SIZE)
            .build();
    }

    public static PostResponse getPostResponse() {
        return PostResponse.builder()
            .id(1L)
            .authorId(1L)
            .authorImage(AUTHOR_IMAGE)
            .authorNickname(AUTHOR_NICKNAME)
            .title(TITLE)
            .contents(CONTENTS)
            .imageUrls(List.of(IMAGE_URL))
            .createdDate(DATE_TIME)
            .build();
    }

    public static PostCardResponse getPostCardResponse() {
        return PostCardResponse.builder()
            .id(1L)
            .authorImage(AUTHOR_IMAGE)
            .authorNickname(AUTHOR_NICKNAME)
            .title(TITLE)
            .contents(CONTENTS)
            .createdDate(DATE_TIME)
            .commentCnt(1L)
            .build();
    }
}
