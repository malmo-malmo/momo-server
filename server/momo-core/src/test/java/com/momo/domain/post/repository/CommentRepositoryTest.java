package com.momo.domain.post.repository;

import static com.momo.CommentFixture.getComment;
import static com.momo.GroupFixture.getGroup;
import static com.momo.PostFixture.getPost;
import static com.momo.UserFixture.getUser;
import static com.momo.post.entity.PostType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.group.entity.Group;
import com.momo.post.entity.Comment;
import com.momo.post.entity.Post;
import com.momo.post.repository.CommentRepository;
import com.momo.user.domain.model.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    void 게시글에_등록된_댓글_목록을_조회한다() {
        List<Comment> actual = commentRepository.findAllByPostOrderByIdDesc(post, comment.getId(), 10);
        assertThat(actual.size()).isEqualTo(0L);
    }

    @Test
    void 게시글에_등록된_댓글_목록을_조회한다_마지막_댓글_ID_NULL() {
        List<Comment> actual = commentRepository.findAllByPostOrderByIdDesc(post, null, 10);
        assertThat(actual.size()).isEqualTo(1L);
    }

    @Test
    void 게시글에_등록된_댓글_개수를_조회한다() {
        long actual = commentRepository.countByPost(post);
        assertThat(actual).isEqualTo(1L);
    }
}
