package com.momo.domain.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.RepositoryTest;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.post.domain.model.Image;
import com.momo.domain.post.domain.model.Post;
import com.momo.domain.post.domain.model.PostType;
import com.momo.domain.post.domain.repository.ImageRepository;
import com.momo.domain.user.domain.model.SocialProvider;
import com.momo.domain.user.domain.model.User;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("이미지 레포지토리 테스트")
public class ImageRepositoryTest extends RepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    private User user;

    private Post post;

    private Image image;

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
        Groups group = save(Groups.builder()
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
        image = imageRepository.save(
            Image.builder()
                .post(post)
                .imageUrl("http://~~")
                .build()
        );
    }

    @Test
    public void 이미지를_저장한다() {
        Image findImage = imageRepository.findAll().get(0);

        Assertions.assertAll(
            () -> assertThat(findImage).isEqualTo(image),
            () -> assertThat(findImage.getPost()).isEqualTo(post),
            () -> assertThat(findImage.getImageUrl()).isEqualTo("http://~~")
        );
    }
}
