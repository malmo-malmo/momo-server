package com.momo.user.domain.model;

import com.momo.common.domain.BaseEntity;
import com.momo.common.domain.GroupCategory;
import java.util.List;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String providerId;

    @Enumerated(EnumType.STRING)
    private SocialProvider provider;

    private String nickname;

    private String profileImg;

    @Enumerated(EnumType.STRING)
    private Location location;

    private String university;

    private String groupCategories;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String providerId, SocialProvider provider, String nickname,
        String profileImg, Location location, String university, String groupCategories,
        Role role) {
        this.id = id;
        this.providerId = providerId;
        this.provider = provider;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.location = location;
        this.university = university;
        this.groupCategories = groupCategories;
        this.role = role;
    }

    public String getRoleName() {
        return this.role.name();
    }

    public boolean isNotSameNickname(String nickname) {
        return !this.nickname.equals(nickname);
    }

    public void update(User user) {
        this.nickname = user.getNickname();
        this.location = user.getLocation();
        this.university = user.getUniversity();
    }

    public void updateGroupCategories(List<GroupCategory> categories) {
        this.groupCategories = StringUtils.join(categories, ",");
    }
}
