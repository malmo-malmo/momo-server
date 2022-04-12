package com.momo.user.domain;

import static java.util.Objects.isNull;

import com.momo.common.entity.BaseEntity;
import com.momo.favorite.entity.FavoriteCategories;
import com.momo.group.domain.category.Category;
import com.momo.user.domain.location.Location;
import com.momo.user.domain.social.SocialLogin;
import java.util.List;
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
    private SocialLogin socialLogin;

    @Embedded
    private Location location;

    @Embedded
    private final FavoriteCategories favoriteCategories = FavoriteCategories.empty();

    @Builder
    public User(Long id, SocialLogin socialLogin, String nickname, String imageUrl, Location location) {
        this.id = id;
        this.socialLogin = socialLogin;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.location = location;
    }

    public static User createSocialLoginUser(SocialLogin socialLogin) {
        return User.builder()
            .socialLogin(socialLogin)
            .build();
    }

    public boolean isSameNickname(String nickname) {
        if (isNull(this.nickname)) {
            return true;
        }
        return this.nickname.equals(nickname);
    }

    public boolean isSameUser(User user) {
        return this.isSameId(user.getId());
    }

    public void update(String nickname, Location location) {
        this.nickname = nickname;
        updateLocation(location);
    }

    private void updateLocation(Location location) {
        if (isNull(this.location)) {
            this.location = Location.create(location);
            return;
        }
        this.location.update(location);
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateFavoriteCategories(List<Category> categories) {
        favoriteCategories.updateAll(this, categories);
    }

    public boolean isSameId(Long userId) {
        return this.id.equals(userId);
    }
}