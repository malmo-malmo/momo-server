package com.momo.unit.group.domain;

import static com.momo.GroupFixture.getGroup;
import static com.momo.GroupFixture.getGroupSearchConditionRequest;
import static com.momo.UserFixture.getUser;
import static com.momo.group.domain.category.Category.HEALTH;
import static com.momo.group.domain.category.Category.STOCK;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.RepositoryTest;
import com.momo.group.application.dto.response.GroupCardResponse;
import com.momo.group.application.dto.response.GroupResponse;
import com.momo.group.application.dto.request.GroupSearchConditionRequest;
import com.momo.group.domain.Group;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.user.domain.User;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@DisplayName("모임 레포지토리 테스트")
public class GroupRepositoryTest extends RepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    private User user;
    private Group group1;
    private Group group2;

    @BeforeEach
    void before() {
        user = save(getUser());
        group1 = save(getGroup(user));
        group2 = save(getGroup(user));
        save(getGroup(user, true));
    }

    @Test
    void 모임을_저장한다() {
        Group actual = groupRepository.findAll().get(0);
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.getLocation().getCity()).isEqualTo(group1.getLocation().getCity()),
            () -> assertThat(actual.getLocation().getDistrict()).isEqualTo(group1.getLocation().getDistrict()),
            () -> assertThat(actual.getImageUrl()).isEqualTo(group1.getImageUrl()),
            () -> assertThat(actual.getIntroduction()).isEqualTo(group1.getIntroduction()),
            () -> assertThat(actual.getLocation().getUniversity()).isEqualTo(group1.getLocation().getUniversity()),
            () -> assertThat(actual.isOffline()).isEqualTo(group1.isOffline()),
            () -> assertThat(actual.isEnd()).isEqualTo(group1.isEnd()),
            () -> assertThat(actual.getStartDate()).isEqualTo(group1.getStartDate()),
            () -> assertThat(actual.getName()).isEqualTo(group1.getName()),
            () -> assertThat(actual.getManager().getId()).isEqualTo(user.getId())
        );
    }

    @Test
    void 사용자ID와_모임ID로_모임을_조회한다() {
        GroupResponse response = groupRepository.findDetailByGroupId(user, group1.getId());
        Assertions.assertAll(
            () -> assertThat(response).isNotNull(),
            () -> assertThat(response.getId()).isEqualTo(group1.getId()),
            () -> assertThat(response.getManagerId()).isEqualTo(user.getId()),
            () -> assertThat(response.getName()).isEqualTo(group1.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo(group1.getImageUrl()),
            () -> assertThat(response.getStartDate()).isEqualTo(group1.getStartDate()),
            () -> assertThat(response.getUniversity()).isEqualTo(group1.getLocation().getUniversity()),
            () -> assertThat(response.getCity()).isEqualTo(group1.getLocation().getCity().getName()),
            () -> assertThat(response.getDistrict()).isEqualTo(group1.getLocation().getDistrict()),
            () -> assertThat(response.isOffline()).isEqualTo(group1.isOffline()),
            () -> assertThat(response.getIntroduction()).isEqualTo(group1.getIntroduction()),
            () -> assertThat(response.getRecruitmentCnt()).isEqualTo(group1.getRecruitmentCnt()),
            () -> assertThat(response.isEnd()).isEqualTo(group1.isEnd())
        );
    }

    @Test
    void 검색_조건으로_모임을_조회한다_엘라스틱서치X_키워드O() {
        GroupSearchConditionRequest request = getGroupSearchConditionRequest("이름");
        List<GroupCardResponse> actual = groupRepository
            .findAllBySearchConditionOrderByCreatedDateDesc(user, request, PageRequest.of(0, 10));

        verifyGroupSearchResponse(actual);
    }

    @Test
    void 검색_조건으로_모임을_조회한다_엘라스틱서치X_키워드_X() {
        GroupSearchConditionRequest request = getGroupSearchConditionRequest(null);
        List<GroupCardResponse> actual = groupRepository.findAllByCitiesAndCategoriesOrderByCreatedDateDesc(
            user, request.getCities(), request.getCategories(), PageRequest.of(0, 10)
        );

        verifyGroupSearchResponse(actual);
    }

    @Test
    void 학교로_모임_목록을_조회한다() {
        List<GroupCardResponse> actual = groupRepository
            .findByUniversityOrderByIdDesc(user, user.getLocation().getUniversity(), group2.getId(), 10);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isEqualTo(group1.getId())
        );
    }

    @Test
    void 학교로_모임_목록을_조회한다_마지막_모임_ID가_없는_경우() {
        List<GroupCardResponse> actual = groupRepository
            .findByUniversityOrderByIdDesc(user, user.getLocation().getUniversity(), null, 10);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2)
        );
    }

    @Test
    void 구역으로_모임_목록을_조회한다() {
        List<GroupCardResponse> actual = groupRepository
            .findByDistrictOrderByIdDesc(user, group1.getLocation().getDistrict(), group2.getId(), 10);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isEqualTo(group1.getId())
        );
    }

    @Test
    void 구역으로_모임_목록을_조회한다_마지막_모임_ID가_없는_경우() {
        List<GroupCardResponse> actual = groupRepository
            .findByDistrictOrderByIdDesc(user, group1.getLocation().getDistrict(), null, 10);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2)
        );
    }

    @Test
    void 카테고리_목록으로_모임_목록을_조회한다() {
        List<GroupCardResponse> actual = groupRepository
            .findByCategoriesOrderByIdDesc(user, of(HEALTH, STOCK), group2.getId(), 10);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(1),
            () -> assertThat(actual.get(0).getId()).isEqualTo(group1.getId())
        );
    }

    @Test
    void 카테고리_목록으로_모임_목록을_조회한다_마지막_모임_ID가_없는_경우() {
        List<GroupCardResponse> actual = groupRepository
            .findByCategoriesOrderByIdDesc(user, of(HEALTH, STOCK), null, 10);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2)
        );
    }

    @Test
    void 관리하는_모임_목록을_조회한다() {
        List<Group> actual = groupRepository.findAllByManagerAndIsEnd(user, false);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2)
        );
    }

    @Test
    void 관리하는_모임과_달성률_목록을_조회한다() {
        List<Group> actual = groupRepository.findGroupAndAchievementRateByUser(user);

        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2),
            () -> assertThat(actual.get(0).getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.get(0).getAchievementRate().getRate()).isEqualTo(BigDecimal.ZERO),
            () -> assertThat(actual.get(1).getId()).isEqualTo(group2.getId()),
            () -> assertThat(actual.get(1).getAchievementRate().getRate()).isEqualTo(BigDecimal.ZERO)
        );
    }

    private void verifyGroupSearchResponse(List<GroupCardResponse> actual) {
        Assertions.assertAll(
            () -> assertThat(actual).isNotNull(),
            () -> assertThat(actual.size()).isEqualTo(2),
            () -> assertThat(actual.get(0).getId()).isEqualTo(group2.getId()),
            () -> assertThat(actual.get(0).getName()).isEqualTo(group2.getName()),
            () -> assertThat(actual.get(0).getImageUrl()).isEqualTo(group2.getImageUrl()),
            () -> assertThat(actual.get(0).isOffline()).isEqualTo(group2.isOffline()),
            () -> assertThat(actual.get(0).getStartDate()).isEqualTo(group2.getStartDate()),
            () -> assertThat(actual.get(0).getParticipantCnt()).isEqualTo(0),
            () -> assertThat(actual.get(0).isFavoriteGroup()).isEqualTo(false),
            () -> assertThat(actual.get(1).getId()).isEqualTo(group1.getId()),
            () -> assertThat(actual.get(1).getName()).isEqualTo(group1.getName()),
            () -> assertThat(actual.get(1).getImageUrl()).isEqualTo(group1.getImageUrl()),
            () -> assertThat(actual.get(1).isOffline()).isEqualTo(group1.isOffline()),
            () -> assertThat(actual.get(1).getStartDate()).isEqualTo(group1.getStartDate()),
            () -> assertThat(actual.get(1).getParticipantCnt()).isEqualTo(0),
            () -> assertThat(actual.get(1).isFavoriteGroup()).isEqualTo(false)
        );
    }
}
