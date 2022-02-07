package com.momo;

import com.momo.domain.group.entity.Group;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostUpdateRequest;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class PostFixture {

    private static final String TITLE = "제목";
    private static final String CONTENTS = "내용";

    public static final List<MultipartFile> IMAGE_FILES = List.of(
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
            .images(IMAGE_FILES)
            .build();
    }

    public static PostUpdateRequest getPostUpdateRequest(Long postId) {
        INCREASE_ID++;
        return PostUpdateRequest.builder()
            .postId(postId)
            .title(TITLE + INCREASE_ID)
            .contents(CONTENTS + INCREASE_ID)
            .build();
    }
}
