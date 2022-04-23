package com.momo.post.entity;

import com.momo.common.entity.BaseEntity;
import com.momo.group.domain.Group;
import com.momo.user.domain.User;
import java.util.List;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "user_fk_post"))
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_tb_fk_post"))
    private Group group;

    private String title;

    @Lob
    private String contents;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @Embedded
    private final Images images = Images.empty();

    @Builder
    public Post(Long id, User author, Group group, String title, String contents, PostType type) {
        this.id = id;
        this.author = author;
        this.group = group;
        this.title = title;
        this.contents = contents;
        this.type = type;
    }

    public static Post create(User user, Group group, Post post) {
        return Post.builder()
            .author(user)
            .group(group)
            .title(post.getTitle())
            .contents(post.getContents())
            .type(post.getType())
            .build();
    }

    public void updateImages(List<String> imageUrls) {
        images.updateAll(this, imageUrls);
    }

    public void updateTitleAndContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public boolean isAuthor(User loginUser) {
        if (Objects.isNull(loginUser)) {
            return false;
        }

        return this.author.equals(loginUser);
    }
}
