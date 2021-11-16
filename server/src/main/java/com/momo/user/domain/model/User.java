package com.momo.user.domain.model;

import com.momo.common.domain.BaseEntity;
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

  private String area;

  private String university;

  private String favoriteCategory;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  public User(Long id, String providerId, SocialProvider provider, String nickname,
      String profileImg, String area, String university, String favoriteCategory, Role role) {
    this.id = id;
    this.providerId = providerId;
    this.provider = provider;
    this.nickname = nickname;
    this.profileImg = profileImg;
    this.area = area;
    this.university = university;
    this.favoriteCategory = favoriteCategory;
    this.role = role;
  }

  public String getRoleName() {
    return this.role.name();
  }
}
