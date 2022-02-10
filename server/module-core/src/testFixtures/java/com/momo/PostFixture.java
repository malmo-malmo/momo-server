package com.momo;

import static com.momo.common.CommonFixtures.UPLOAD_TEST_FILE;

import com.momo.domain.group.entity.Group;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.dto.PostCardsRequest;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.dto.PostUpdateRequest;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class PostFixture {

    private static final String TITLE = "제목";
    private static final String CONTENTS = "내용";
    private static final String AUTHOR_IMAGE = "이미지";
    private static final String AUTHOR_NICKNAME = "닉네임";
    private static final String IMAGE_URL = "이미지 URL";

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 10;

    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2022, 1, 1, 1, 0);

    public static final List<MultipartFile> IMAGE_FILES = List.of(
        new MockMultipartFile("image", "image".getBytes())
    );
    public static final List<MultipartFile> UPDATE_IMAGE_FILES = List.of(
        new MockMultipartFile("image", "image".getBytes())
    );

    private static Long INCREASE_ID = 0L;

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
            .images(List.of(UPLOAD_TEST_FILE))
            .build();
    }

    public static PostUpdateRequest getPostUpdateRequest(Long postId) {
        INCREASE_ID++;
        return PostUpdateRequest.builder()
            .postId(postId)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .images(UPDATE_IMAGE_FILES)
            .build();
    }

    public static PostCardsRequest getPostCardsRequest(Long groupId, PostType postType) {
        return PostCardsRequest.builder()
            .groupId(groupId)
            .postType(postType)
            .page(PAGE)
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
