package com.momo.domain.post.domain.model;

import com.momo.domain.common.domain.BaseEntity;
import com.momo.domain.user.domain.model.User;
import java.time.LocalDateTime;
import javax.persistence.Entity;
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
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "post_fk_comment"))
    private Post post;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk_comment"))
    private User user;

    @Lob
    private String contents;

    @Builder
    public Comment(Long id, Post post, User user, String contents, LocalDateTime createdDate,
        LocalDateTime lastModifiedDate) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.post = post;
        this.user = user;
        this.contents = contents;
    }

    public static Comment create(Post post, User user, String contents) {
        return Comment.builder()
            .post(post)
            .user(user)
            .contents(contents)
            .build();
    }
}
