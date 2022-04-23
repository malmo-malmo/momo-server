package com.momo.unit.favorite.repository;

import static com.momo.FavoriteFixture.getFavoriteGroup;
import static com.momo.GroupFixture.getGroup;
import static com.momo.ParticipantFixture.getParticipant;
import static com.momo.UserFixture.getUser;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.favorite.dto.FavoriteGroupCardResponse;
import com.momo.favorite.entity.FavoriteGroup;
import com.momo.favorite.repository.FavoriteGroupRepository;
import com.momo.group.domain.Group;
import com.momo.user.domain.User;
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
        user = save(getUser());
        group1 = save(getGroup(user));
        group2 = save(getGroup(user));
    }

    @Test
    void 관심_모임으로_등록한다() {
        FavoriteGroup favoriteGroup = getFavoriteGroup(user, group1);

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
        List<FavoriteGroup> favoriteGroups = List.of(getFavoriteGroup(user, group1), getFavoriteGroup(user, group2));
        favoriteGroupRepository.saveAll(favoriteGroups);

        Long actual = favoriteGroupRepository.countByUser(user);

        assertThat(actual).isEqualTo(favoriteGroups.size());
    }

    @Test
    void 관심_모임_목록을_조회한다() {
        save(getParticipant(group1, user));
        save(getFavoriteGroup(user, group1));

        List<FavoriteGroupCardResponse> actual = favoriteGroupRepository.findAllByUserOrderByCreatedDateDesc(user);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isNotNull(),
            () -> assertThat(actual.get(0).getGroupCardResponse().getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getName()).isEqualTo(group1.getName()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getImageUrl()).isEqualTo(group1.getImageUrl()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getStartDate()).isEqualTo(group1.getStartDate()),
            () -> assertThat(actual.get(0).getGroupCardResponse().isOffline()).isEqualTo(group1.isOffline()),
            () -> assertThat(actual.get(0).getGroupCardResponse().getParticipantCnt()).isEqualTo(1),
            () -> assertThat(actual.get(0).getGroupCardResponse().isFavoriteGroup()).isEqualTo(true)
        );
    }

    @Test
    void 관심_모임을_삭제한다() {
        FavoriteGroup favoriteGroup = getFavoriteGroup(user, group1);
        favoriteGroupRepository.deleteByUserAndGroup(user, favoriteGroup.getGroup());

        List<FavoriteGroup> actual = favoriteGroupRepository.findAll();

        assertThat(actual.size()).isEqualTo(0);
    }
}
