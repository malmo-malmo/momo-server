package com.momo.unit.post.entity;

import static com.momo.GroupFixture.getGroupWithId;
import static com.momo.PostFixture.getPostWithId;
import static com.momo.UserFixture.getUserWithId;
import static com.momo.group.domain.post.PostType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.group.domain.post.comment.Comment;
import com.momo.group.domain.post.Post;
import com.momo.user.domain.User;
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
        user = getUserWithId();
        post = getPostWithId(user, getGroupWithId(user), NORMAL);
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
