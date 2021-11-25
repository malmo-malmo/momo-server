package com.momo.post.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.group.domain.model.Groups;
import com.momo.user.domain.model.User;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Groups group;

    private String title;

    @Lob
    private String contents;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @Builder
    public Post(Long id, User author, Groups group, String title, String contents, PostType type) {
        this.id = id;
        this.author = author;
        this.group = group;
        this.title = title;
        this.contents = contents;
        this.type = type;
    }

    public static Post create(User user, Groups group, Post post) {
        return Post.builder()
            .author(user)
            .group(group)
            .title(post.getTitle())
            .contents(post.getContents())
            .type(post.getType())
            .build();
    }
}
