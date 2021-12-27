package com.momo.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.post.domain.model.Comment;
import com.momo.domain.post.domain.model.Post;
import com.momo.domain.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Comment POJO 테스트")
public class CommentTest {

    private Post post;

    private User user;

    @BeforeEach
    void setUp() {
        post = Post.builder().id(1L).build();
        user = User.builder().id(1L).build();
    }

    @Test
    void 댓글_생성_테스트() {
        Comment expected = Comment.create(post, user, "내용");
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getPost().getId()).isEqualTo(post.getId()),
            () -> assertThat(expected.getUser().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getContents()).isEqualTo("내용")
        );
    }
}
