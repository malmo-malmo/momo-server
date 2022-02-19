package com.momo.domain.user.entity;

import com.momo.domain.common.entity.BaseEntity;
import com.momo.domain.favorite.entity.FavoriteCategories;
import com.momo.domain.group.entity.Category;
import java.util.List;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String imageUrl;

    @Embedded
    private LoginInfo loginInfo;

    @Embedded
    private Location location;

    @Embedded
    private final FavoriteCategories favoriteCategories = FavoriteCategories.empty();

    @Builder
    public User(Long id, LoginInfo loginInfo, String nickname, String imageUrl, Location location) {
        this.id = id;
        this.loginInfo = loginInfo;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public static User createSocialLoginUser(LoginInfo loginInfo) {
        return User.builder()
            .loginInfo(loginInfo)
            .build();
    }

    public boolean isSameNickname(String nickname) {
        if (Objects.isNull(this.nickname)) {
            return true;
        }
        return this.nickname.equals(nickname);
    }

    public boolean isSameUser(User user) {
        return id.equals(user.getId());
    }

    public void update(User user, String imageUrl) {
        this.nickname = user.getNickname();
        this.imageUrl = imageUrl;
        this.location.update(user.getLocation());
    }

    public void updateFavoriteCategories(List<Category> categories) {
        favoriteCategories.updateAll(this, categories);
    }
}
