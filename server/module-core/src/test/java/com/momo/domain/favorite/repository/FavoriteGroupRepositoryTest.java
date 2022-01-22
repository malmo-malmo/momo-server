package com.momo.domain.favorite.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.favorite.dto.FavoriteGroupCardResponse;
import com.momo.domain.favorite.entity.FavoriteGroup;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.entity.Group;
import com.momo.domain.group.entity.Participant;
import com.momo.domain.user.entity.SocialProvider;
import com.momo.domain.user.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("관심 모임 레포지토리 테스트")
public class FavoriteGroupRepositoryTest extends RepositoryTest {

    @Autowired
    private FavoriteGroupRepository favoriteGroupRepository;

    private User user;

    private Group group1;

    private Group group2;

    @BeforeEach
    void setUp() {
        user = save(
            User.builder()
                .provider(SocialProvider.KAKAO)
                .providerId("test")
                .refreshToken("refresh Token")
                .nickname("testMan")
                .imageUrl("imageUrl")
                .city(City.SEOUL)
                .district("마포구")
                .university("서울대학교")
                .build()
        );
        group1 = save(
            Group.builder()
                .city(City.SEOUL)
                .district("마포구")
                .imageUrl("imageUrl")
                .introduction("안녕하세요")
                .university("한국대")
                .isOffline(false)
                .isEnd(false)
                .startDate(LocalDate.now())
                .name("모임 이름")
                .category(Category.LIFE)
                .manager(user)
                .build()
        );
        group2 = save(
            Group.builder()
                .city(City.SEOUL)
                .district("은평구")
                .imageUrl("imageUrl")
                .introduction("안녕하세요")
                .university("서울대학교")
                .isOffline(true)
                .isEnd(false)
                .startDate(LocalDate.now())
                .name("모임 이름")
                .category(Category.HEALTH)
                .manager(user)
                .build()
        );
    }

    @Test
    void 관심_모임으로_등록한다() {
        FavoriteGroup favoriteGroup = FavoriteGroup.builder()
            .user(user)
            .group(group1)
            .build();

        FavoriteGroup actual = favoriteGroupRepository.save(favoriteGroup);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isNotNull(),
            () -> assertThat(actual.getUser().getId()).isEqualTo(user.getId()),
            () -> assertThat(actual.getGroup().getId()).isEqualTo(group1.getId())
        );
    }

    @Test
    void 관심_모임_수를_조회한다() {
        List<FavoriteGroup> favoriteGroups = List.of(
            FavoriteGroup.builder()
                .user(user)
                .group(group1)
                .build(),
            FavoriteGroup.builder()
                .user(user)
                .group(group2)
                .build()
        );
        favoriteGroupRepository.saveAll(favoriteGroups);

        Long actual = favoriteGroupRepository.countByUser(user);

        assertThat(actual).isEqualTo(favoriteGroups.size());
    }

    @Test
    void 관심_모임_목록을_조회한다() {
        save(
            Participant.builder()
                .user(user)
                .group(group1)
                .build()
        );
        save(
            Participant.builder()
                .user(user)
                .group(group2)
                .build()
        );
        favoriteGroupRepository.saveAll(
            List.of(
                FavoriteGroup.builder()
                    .user(user)
                    .group(group1)
                    .createdDate(LocalDateTime.of(2022, 1, 1, 1, 1))
                    .build(),
                FavoriteGroup.builder()
                    .user(user)
                    .group(group2)
                    .createdDate(LocalDateTime.of(2022, 1, 1, 1, 2))
                    .build()
            )
        );

        List<FavoriteGroupCardResponse> actual = favoriteGroupRepository.findAllByUserOrderByCreatedDateDesc(user);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2),
            () -> assertThat(actual.get(0).getId()).isNotNull(),
            () -> assertThat(actual.get(0).getGroupCardResponse().getId()).isEqualTo(group2.getId()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getName()).isEqualTo(group2.getName()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getImageUrl()).isEqualTo(group2.getImageUrl()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getStartDate()).isEqualTo(group2.getStartDate()),
            () -> assertThat(actual.get(0).getGroupCardResponse().isOffline()).isEqualTo(group2.isOffline()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getParticipantCnt()).isEqualTo(1),
            () -> assertThat(actual.get(0).getGroupCardResponse().isFavoriteGroup()).isEqualTo(true),
            () -> assertThat(actual.get(1).getId()).isNotNull(),
            () -> assertThat(actual.get(1).getGroupCardResponse().getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.get(1).getGroupCardResponse().getName()).isEqualTo(group1.getName()),
            () -> assertThat(actual.get(1).getGroupCardResponse().getImageUrl()).isEqualTo(group1.getImageUrl()),
            () -> assertThat(actual.get(1).getGroupCardResponse().getStartDate()).isEqualTo(group1.getStartDate()),
            () -> assertThat(actual.get(1).getGroupCardResponse().isOffline()).isEqualTo(group1.isOffline()),
            () -> assertThat(actual.get(1).getGroupCardResponse().getParticipantCnt()).isEqualTo(1),
            () -> assertThat(actual.get(1).getGroupCardResponse().isFavoriteGroup()).isEqualTo(true)
        );
    }

    @Test
    void 관심_모임을_삭제한다() {
        FavoriteGroup favoriteGroup = favoriteGroupRepository.save(
            FavoriteGroup.builder()
                .user(user)
                .group(group1)
                .build()
        );

        favoriteGroupRepository.deleteByUserAndGroupId(user, favoriteGroup.getId());
        List<FavoriteGroup> actual = favoriteGroupRepository.findAll();

        assertThat(actual.size()).isEqualTo(0);
    }
}
