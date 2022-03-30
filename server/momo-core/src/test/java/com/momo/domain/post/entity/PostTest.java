package com.momo.domain.post.entity;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.PostFixture.getPost;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.post.entity.PostType.NORMAL;
import static com.momo.post.entity.PostType.NOTICE;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.entity.Group;
import com.momo.post.entity.Post;
import com.momo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Post POJO 테스트")
public class PostTest {

    private User user;
    private Group group;

    @BeforeEach
    void setUp() {
        user = getUserWithId();
        group = getGroupWithId(user);
    }

    @Test
    void 게시물_생성_테스트() {
        Post post = getPost(user, group, NORMAL);
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
        Post post = getPost(user, group, NOTICE);
        Post expected = Post.create(user, group, post);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getType()).isEqualTo(post.getType()),
            () -> assertThat(expected.getAuthor().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getGroup().getId()).isEqualTo(group.getId())
        );
    }
}
