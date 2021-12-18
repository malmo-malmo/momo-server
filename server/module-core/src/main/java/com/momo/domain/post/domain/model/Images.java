package com.momo.domain.post.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Images {

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public static Images empty() {
        return new Images();
    }

    public void updateAll(Post post, List<String> imageUrls) {
        if (images.size() > 0) {
            deleteAll();
        }
        for (String imageUrl : imageUrls) {
            Image image = Image.create(post, imageUrl);
            images.add(image);
        }
    }

    private void deleteAll() {
        images.clear();
    }

    public List<String> toImageUrls() {
        return images.stream()
            .map(Image::getImageUrl)
            .collect(Collectors.toList());
    }
}
