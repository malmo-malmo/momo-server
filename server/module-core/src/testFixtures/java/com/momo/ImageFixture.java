package com.momo;

import com.momo.domain.post.entity.Image;
import com.momo.domain.post.entity.Post;

public class ImageFixture {

    private static final String IMAGE_URL = "이미지 URL";

    private static Long INCREASE_ID = 0L;

    public static Image getImage(Post post) {
        INCREASE_ID++;
        return Image.builder()
            .post(post)
            .imageUrl(IMAGE_URL + INCREASE_ID)
            .build();
    }

    public static Image getImageWithId(Post post) {
        INCREASE_ID++;
        return Image.builder()
            .id(INCREASE_ID)
            .post(post)
            .imageUrl(IMAGE_URL + INCREASE_ID)
            .build();
    }
}
