package com.momo.domain.post.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Group;
import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.entity.Post;
import com.momo.domain.post.entity.PostType;
import com.momo.domain.user.entity.Location;
import com.momo.domain.user.entity.Social;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DisplayName("댓글 레포지토리 테스트")
public class CommentRepositoryTest extends RepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    private Comment comment;

    private User user;

    private Post post;

    @BeforeEach
    public void before() {
        user = save(
            User.builder()
                .loginInfo(Social.from(SocialProvider.KAKAO, "test", "refresh Token"))
                .nickname("testMan")
                .imageUrl("http://~~")
                .location(Location.builder()
                    .city(City.SEOUL)
                    .district("마포구")
                    .university("한국대")
                    .build())
                .build()
        );
        Group group = save(Group.builder()
            .location(Location.builder()
                .university("한국대")
                .city(City.SEOUL)
                .district("마포")
                .build())
            .imageUrl("http://~")
            .introduction("안녕하세요")
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
        this.comment = commentRepository.save(
            Comment.builder()
                .post(post)
                .user(user)
                .contents("테스트 댓글 내용")
                .build()
        );
    }

    @Test
    public void 댓글을_저장한다() {
        Comment findComment = commentRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(findComment).isEqualTo(comment),
            () -> assertThat(findComment.getId()).isEqualTo(comment.getId()),
            () -> assertThat(findComment.getPost()).isEqualTo(post),
            () -> assertThat(findComment.getUser()).isEqualTo(user),
            () -> assertThat(findComment.getContents()).isEqualTo("테스트 댓글 내용")
        );
    }

    @Test
    public void 게시글에_해당하는_댓글_목록_조회한다() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Comment> comments = commentRepository.findAllByPostOrderByCreatedDateAsc(post, pageRequest);
        assertThat(comments.getTotalElements()).isEqualTo(1L);
    }
}
