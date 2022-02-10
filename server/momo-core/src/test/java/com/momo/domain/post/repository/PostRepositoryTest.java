package com.momo.domain.post.repository;

import static com.momo.GroupFixture.getGroup;
import static com.momo.PostFixture.getPost;
import static com.momo.UserFixture.getUser;
import static com.momo.domain.post.entity.PostType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.PageRequest.of;

import com.momo.common.RepositoryTest;
import com.momo.domain.group.entity.Group;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.entity.Post;
import com.momo.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("게시글 레포지토리 테스트")
public class PostRepositoryTest extends RepositoryTest {

    @Autowired
    private PostRepository postRepository;

    private User user;
    private Group group;
    private Post post;

    @BeforeEach
    void before() {
        user = save(getUser());
        group = save(getGroup(user));
        post = save(getPost(user, group, NORMAL));
    }

    @Test
    void 게시글을_저장한다() {
        Post expected = postRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getId()).isEqualTo(post.getId()),
            () -> assertThat(expected.getAuthor().getId()).isEqualTo(user.getId()),
            () -> assertThat(expected.getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(expected.getTitle()).isEqualTo(post.getTitle()),
            () -> assertThat(expected.getContents()).isEqualTo(post.getContents()),
            () -> assertThat(expected.getType()).isEqualTo(post.getType())
        );
    }

    @Test
    void 게시글_번호로_게시글을_조회한다() {
        Long actual = post.getId();
        Long expected = postRepository.findPostAndAuthorById(actual).getId();
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void 게시글_목록을_조회한다() {
        List<PostCardResponse> actual = postRepository
            .findAllWithAuthorByGroupAndTypeOrderByCreatedDateDesc(group, post.getType(), of(0, 10));
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isNotNull(),
            () -> assertThat(actual.get(0).getAuthorImage()).isEqualTo(user.getImageUrl()),
            () -> assertThat(actual.get(0).getAuthorNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(actual.get(0).getTitle()).isEqualTo(post.getTitle()),
            () -> assertThat(actual.get(0).getContents()).isEqualTo(post.getContents()),
            () -> assertThat(actual.get(0).getCommentCnt()).isEqualTo(0)
        );
    }

    @Test
    void 자신이_올린_게시글을_조회한다() {
        List<Post> actual = postRepository
            .findAllWithGroupAndAuthorByUserAndTypeOrderByCreatedDateDesc(user, NORMAL, of(0, 10));
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isNotNull(),
            () -> assertThat(actual.get(0).getAuthor().getId()).isEqualTo(user.getId()),
            () -> assertThat(actual.get(0).getAuthor().getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(actual.get(0).getGroup().getId()).isEqualTo(group.getId()),
            () -> assertThat(actual.get(0).getGroup().getName()).isEqualTo(group.getName()),
            () -> assertThat(actual.get(0).getTitle()).isEqualTo(post.getTitle()),
            () -> assertThat(actual.get(0).getContents()).isEqualTo(post.getContents())
        );
    }
}
