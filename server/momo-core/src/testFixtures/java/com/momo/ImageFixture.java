package com.momo;

import static com.momo.common.FixtureComponents.IMAGE_URL;
import static com.momo.common.FixtureComponents.INCREASE_ID;

import com.momo.post.entity.Image;
import com.momo.post.entity.Post;

public class ImageFixture {

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
