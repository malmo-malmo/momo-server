package com.momo.post.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.group.domain.model.Groups;
import com.momo.user.domain.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.Formula;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Groups group;

    private String title;

    @Lob
    private String contents;

    @Formula("(select count(*) post_comment c where c.post_id = post_id)")
    private int commentCnt;

    @Builder
    public Post(Long id, User user, Groups group, String title, String contents) {
        this.id = id;
        this.user = user;
        this.group = group;
        this.title = title;
        this.contents = contents;
    }

    public static Post create(User user, Groups group, Post post) {
        return Post.builder()
            .user(user)
            .group(group)
            .title(post.getTitle())
            .contents(post.getContents())
            .build();
    }
}
