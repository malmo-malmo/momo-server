package com.momo.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.RepositoryTest;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.post.domain.model.Post;
import com.momo.domain.post.domain.model.PostType;
import com.momo.domain.post.domain.repository.PostRepository;
import com.momo.domain.user.domain.model.SocialProvider;
import com.momo.domain.user.domain.model.User;
import java.time.LocalDate;
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

    private Groups group;

    private Post post;

    @BeforeEach
    public void before() {
        user = save(
            User.builder()
                .provider(SocialProvider.KAKAO)
                .providerId("test")
                .refreshToken("refresh Token")
                .nickname("testMan")
                .imageUrl("http://~~")
                .city("서울")
                .district("마포구")
                .university("한국대")
                .build()
        );
        group = save(Groups.builder()
            .city("서울")
            .district("마포")
            .imageUrl("http://~")
            .introduction("안녕하세요")
            .university("한국대")
            .isOffline(false)
            .isEnd(false)
            .startDate(LocalDate.now())
            .name("모임 이름")
            .manager(user)
            .build());
        post = save(
            Post.builder()
                .author(user)
                .group(group)
                .title("테스트 게시글")
                .contents("테스트 내용")
                .type(PostType.NORMAL)
                .build()
        );
    }

    @Test
    public void 게시글을_저장한다() {
        Post post = postRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(post).isEqualTo(this.post),
            () -> assertThat(post.getAuthor()).isEqualTo(user),
            () -> assertThat(post.getGroup()).isEqualTo(group),
            () -> assertThat(post.getTitle()).isEqualTo("테스트 게시글"),
            () -> assertThat(post.getContents()).isEqualTo("테스트 내용"),
            () -> assertThat(post.getType()).isEqualTo(PostType.NORMAL)
        );
    }
    @Test
    public void 게시글_번호로_게시글을_조회한다() {
        Long postId = this.post.getId();
        Post post = postRepository.findPostAndAuthorById(postId);

        assertThat(post).isEqualTo(this.post);
    }
}
