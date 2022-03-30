package com.momo.favorite.entity;

import com.momo.group.entity.Category;
import com.momo.user.domain.model.User;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class FavoriteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk_favorite_category"))
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    public FavoriteCategory(User user, Category category) {
        this.user = user;
        this.category = category;
    }

    public static FavoriteCategory create(User user, Category category) {
        return FavoriteCategory.builder()
            .user(user)
            .category(category)
            .build();
    }
}
