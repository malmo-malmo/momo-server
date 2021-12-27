package com.momo.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.post.domain.model.Post;
import com.momo.domain.post.domain.model.PostType;
import com.momo.domain.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Post POJO 테스트")
public class PostTest {

    private User user;

    private Groups group;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).build();
        group = Groups.builder().id(1L).build();
    }

    @Test
    void 게시물_생성_테스트() {
        Post post = Post.builder().type(PostType.NORMAL).build();
        Post expected = Post.create(user, group, post);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getType()).isEqualTo(post.getType()),
            () -> assertThat(expected.getAuthor().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getGroup().getId()).isEqualTo(group.getId())
        );
    }

    @Test
    void 공지사항_생성_테스트() {
        Post post = Post.builder().type(PostType.NOTICE).build();
        Post expected = Post.create(user, group, post);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getType()).isEqualTo(post.getType()),
            () -> assertThat(expected.getAuthor().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getGroup().getId()).isEqualTo(group.getId())
        );
    }
}
