package com.momo.domain.user.entity;

import com.momo.domain.common.entity.BaseEntity;
import com.momo.domain.group.entity.Category;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private SocialProvider provider;

    @Column(nullable = false)
    private String providerId;

    private String refreshToken;

    private String nickname;

    private String imageUrl;

    private String city;

    private String district;

    private String university;

    @Embedded
    private final FavoriteCategories favoriteCategories = FavoriteCategories.empty();

    @Builder
    public User(Long id, SocialProvider provider, String providerId, String refreshToken, String nickname,
        String imageUrl, String city, String district, String university) {
        this.id = id;
        this.provider = provider;
        this.providerId = providerId;
        this.refreshToken = refreshToken;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.city = city;
        this.district = district;
        this.university = university;
    }

    public static User createSocialLoginUser(String providerId, SocialProvider socialProvider) {
        return User.builder()
            .providerId(providerId)
            .provider(socialProvider)
            .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isNotSameNickname(String nickname) {
        if (Objects.isNull(this.nickname)) {
            return true;
        }
        return !this.nickname.equals(nickname);
    }

    public boolean isSameUser(User user) {
        return id.equals(user.getId());
    }

    public void update(User user) {
        this.nickname = user.getNickname();
        this.city = user.getCity();
        this.district = user.getDistrict();
        this.university = user.getUniversity();
    }

    public void updateFavoriteCategories(List<Category> categories) {
        favoriteCategories.updateAll(this, categories);
    }
}
