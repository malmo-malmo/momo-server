package com.momo.domain.post.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "post_fk_image"))
    private Post post;

    private String imageUrl;

    @Builder
    public Image(Long id, Post post, String imageUrl) {
        this.id = id;
        this.post = post;
        this.imageUrl = imageUrl;
    }

    public static Image create(Post post, String imageUrl) {
        return Image.builder()
            .post(post)
            .imageUrl(imageUrl)
            .build();
    }
}
