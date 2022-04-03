package com.momo.unit.post.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.post.entity.Images;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Images POJO 테스트")
public class ImagesTest {

    @Test
    void 이미지_생성_테스트() {
        Images expected = Images.empty();
        assertThat(expected).isNotNull();
    }

    @Test
    void 비어있는_이미지_업데이트_테스트() {
        Images actual = Images.empty();

        List<String> expected = List.of("imageUrl1", "imageUrl2");
        actual.updateAll(null, expected);

        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(actual.getImages().size()),
            () -> assertThat(expected.get(0)).isEqualTo(actual.getImages().get(0).getImageUrl()),
            () -> assertThat(expected.get(1)).isEqualTo(actual.getImages().get(1).getImageUrl())
        );
    }

    @Test
    void 이미지_업데이트_테스트() {
        Images actual = Images.empty();
        actual.updateAll(null, List.of("imageUrl"));

        List<String> expected = List.of("imageUrl1", "imageUrl2");
        actual.updateAll(null, expected);

        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(actual.getImages().size()),
            () -> assertThat(expected.get(0)).isEqualTo(actual.getImages().get(0).getImageUrl()),
            () -> assertThat(expected.get(1)).isEqualTo(actual.getImages().get(1).getImageUrl())
        );
    }

    @Test
    void 이미지_엔티티에서_이미지_URL로_변경_테스트() {
        Images images = Images.empty();
        List<String> actual = List.of("imageUrl1", "imageUrl2");
        images.updateAll(null, actual);

        List<String> expected = images.toImageUrls();

        Assertions.assertAll(
            () -> assertThat(expected.size()).isEqualTo(actual.size()),
            () -> assertThat(expected.get(0)).isEqualTo(actual.get(0)),
            () -> assertThat(expected.get(1)).isEqualTo(actual.get(1))
        );
    }
}
