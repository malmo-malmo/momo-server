package com.momo.domain.post.repository;

import static com.momo.CommentFixture.getComment;
import static com.momo.GroupFixture.getGroup;
import static com.momo.PostFixture.getPost;
import static com.momo.UserFixture.getUser;
import static com.momo.domain.post.entity.PostType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.PageRequest.of;

import com.momo.common.RepositoryTest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import com.momo.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@DisplayName("댓글 레포지토리 테스트")
public class CommentRepositoryTest extends RepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    private User user;
    private Post post;
    private Comment comment;

    @BeforeEach
    void before() {
        user = save(getUser());
        Group group = save(getGroup(user));
        post = save(getPost(user, group, NORMAL));
        comment = save(getComment(post, user));
    }

    @Test
    void 댓글을_저장한다() {
        Comment expected = commentRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(expected).isEqualTo(comment),
            () -> assertThat(expected.getId()).isEqualTo(comment.getId()),
            () -> assertThat(expected.getPost().getId()).isEqualTo(post.getId()),
            () -> assertThat(expected.getUser().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getContents()).isEqualTo(comment.getContents())
        );
    }

    @Test
    void 게시글에_해당하는_댓글_목록_조회한다() {
        Page<Comment> comments = commentRepository.findAllByPostOrderByCreatedDateAsc(post, of(0, 10));
        assertThat(comments.getTotalElements()).isEqualTo(1L);
    }
}
