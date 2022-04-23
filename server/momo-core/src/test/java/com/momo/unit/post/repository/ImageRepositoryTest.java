package com.momo.unit.post.repository;

import static com.momo.GroupFixture.getGroup;
import static com.momo.ImageFixture.getImage;
import static com.momo.PostFixture.getPost;
import static com.momo.UserFixture.getUser;
import static com.momo.post.entity.PostType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.group.domain.Group;
import com.momo.post.entity.Image;
import com.momo.post.entity.Post;
import com.momo.post.repository.ImageRepository;
import com.momo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("이미지 레포지토리 테스트")
public class ImageRepositoryTest extends RepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    private Image image;

    @BeforeEach
    void setUp() {
        User user = save(getUser());
        Group group = save(getGroup(user));
        Post post = save(getPost(user, group, NORMAL));
        image = save(getImage(post));
    }

    @Test
    void 이미지를_저장한다() {
        Image expected = imageRepository.findAll().get(0);

        Assertions.assertAll(
            () -> assertThat(expected).isNotNull(),
            () -> assertThat(expected.getId()).isEqualTo(image.getId()),
            () -> assertThat(expected.getPost().getId()).isEqualTo(image.getPost().getId()),
            () -> assertThat(expected.getImageUrl()).isEqualTo(image.getImageUrl())
        );
    }
}
