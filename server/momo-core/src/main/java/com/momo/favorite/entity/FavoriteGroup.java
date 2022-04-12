package com.momo.favorite.entity;

import com.momo.common.entity.BaseEntity;
import com.momo.group.domain.Group;
import com.momo.user.domain.User;
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
public class FavoriteGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk_favorite_group"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "group_tb_fk_favorite_group"))
    private Group group;

    @Builder
    public FavoriteGroup(Long id, User user, Group group) {
        this.id = id;
        this.user = user;
        this.group = group;
    }

    public static FavoriteGroup create(User user, Group group) {
        return FavoriteGroup.builder()
            .user(user)
            .group(group)
            .build();
    }
}
