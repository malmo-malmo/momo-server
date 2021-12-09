package com.momo.user.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.group.domain.model.Category;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
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
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    private final static String CATEGORY_SEPARATOR = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String providerId;

    @Enumerated(EnumType.STRING)
    private SocialProvider provider;

    private String nickname;

    private String imageUrl;

    private String city;

    private String district;

    private String university;

    private String categories;

    private String refreshToken;

    @Builder
    public User(Long id, String providerId, SocialProvider provider, String nickname, String imageUrl, String city,
        String district, String university, String categories, String refreshToken) {
        this.id = id;
        this.providerId = providerId;
        this.provider = provider;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.city = city;
        this.district = district;
        this.university = university;
        this.categories = categories;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isNotSameNickname(String nickname) {
        if (Objects.isNull(this.nickname)) {
            return false;
        }
        return !this.nickname.equals(nickname);
    }

    public boolean isSameUser(User user) {
        return id.equals(user.getId());
    }

    public boolean isNotSameUser(User user) {
        return !id.equals(user.getId());
    }

    public void update(User user) {
        this.nickname = user.getNickname();
        this.city = user.getCity();
        this.district = user.getDistrict();
        this.university = user.getUniversity();
    }

    public void updateCategories(String categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return Category.listOf(
            Arrays.asList(StringUtils.split(this.categories, CATEGORY_SEPARATOR))
        );
    }
}
