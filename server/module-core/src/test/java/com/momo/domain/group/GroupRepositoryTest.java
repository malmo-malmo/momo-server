package com.momo.domain.group;

import static org.assertj.core.api.Assertions.assertThat;

import com.momo.RepositoryTest;
import com.momo.domain.group.domain.model.Category;
import com.momo.domain.group.domain.model.Groups;
import com.momo.domain.group.domain.repository.GroupRepository;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.dto.GroupResponse;
import com.momo.domain.user.domain.model.SocialProvider;
import com.momo.domain.user.domain.model.User;
import java.time.LocalDate;
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

    User user;

    Groups group;

    @BeforeEach
    void before() {
        user = save(
            User.builder()
                .provider(SocialProvider.KAKAO)
                .providerId("test")
                .refreshToken("refresh Token")
                .nickname("testMan")
                .imageUrl("http://~~")
                .city("서울")
                .district("마포구")
                .university("한국대")
                .build()
        );
        group = save(Groups.builder()
            .city("서울")
            .district("마포")
            .imageUrl("http://~")
            .introduction("안녕하세요")
            .university("한국대")
            .isOffline(false)
            .isEnd(false)
            .startDate(LocalDate.now())
            .name("모임 이름")
            .category(Category.LIFE)
            .manager(user)
            .build());
    }
    @Test
    public void 모임을_저장한다() {
        Groups group = groupRepository.findAll().get(0);

        Assertions.assertAll(
            () -> assertThat(group).isEqualTo(this.group),
            () -> assertThat(group.getCity()).isEqualTo("서울"),
            () -> assertThat(group.getDistrict()).isEqualTo("마포"),
            () -> assertThat(group.getImageUrl()).isEqualTo("http://~"),
            () -> assertThat(group.getIntroduction()).isEqualTo("안녕하세요"),
            () -> assertThat(group.getUniversity()).isEqualTo("한국대"),
            () -> assertThat(group.isOffline()).isFalse(),
            () -> assertThat(group.isEnd()).isFalse(),
            () -> assertThat(group.getStartDate()).isNotNull(),
            () -> assertThat(group.getName()).isEqualTo("모임 이름"),
            () -> assertThat(group.getManager()).isEqualTo(user)
        );
    }
    @Test
    public void 사용자ID와_모임ID로_모임을_조회한다() {
        GroupResponse response = groupRepository.findGroupAndParticipantCntAndAuthorityById(user, group.getId());

        Assertions.assertAll(
            () -> assertThat(response).isNotNull(),
            () -> assertThat(response.getId()).isEqualTo(group.getId()),
            () -> assertThat(response.getManagerId()).isEqualTo(user.getId()),
            () -> assertThat(response.getName()).isEqualTo(group.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo("http://~"),
            () -> assertThat(response.getStartDate()).isNotNull(),
            () -> assertThat(response.getUniversity()).isEqualTo("한국대"),
            () -> assertThat(response.getCity()).isEqualTo("서울"),
            () -> assertThat(response.getDistrict()).isEqualTo("마포"),
            () -> assertThat(response.isOffline()).isFalse(),
            () -> assertThat(response.getIntroduction()).isEqualTo("안녕하세요"),
            () -> assertThat(response.getRecruitmentCnt()).isEqualTo(0),
            () -> assertThat(response.isEnd()).isFalse()
        );
    }
    @Test
    public void 도시_목록과_카테고리_목록으로_모임_목록을_조회한다() {
        List<GroupCardResponse> groupCardResponses = groupRepository.findAllBySearchConditionOrderByCreatedDateDesc(
            List.of("서울"),
            List.of(Category.LIFE),
            PageRequest.of(0, 10)
        );
        assertThat(groupCardResponses.size()).isEqualTo(1);

        GroupCardResponse response = groupCardResponses.get(0);
        Assertions.assertAll(
            () -> assertThat(response.getId()).isEqualTo(group.getId()),
            () -> assertThat(response.getName()).isEqualTo(group.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo(group.getImageUrl()),
            () -> assertThat(response.getStartDate()).isEqualTo(group.getStartDate()),
            () -> assertThat(response.getParticipantCnt()).isEqualTo(0)
        );
    }
    @Test
    public void 학교로_모임_목록을_조회한다() {
        List<GroupCardResponse> groupCardResponses = groupRepository.findAllByUniversityOrderByCreatedDateDesc("한국대", PageRequest.of(0, 10));
        assertThat(groupCardResponses.size()).isEqualTo(1);

        GroupCardResponse response = groupCardResponses.get(0);
        Assertions.assertAll(
            () -> assertThat(response.getId()).isEqualTo(group.getId()),
            () -> assertThat(response.getName()).isEqualTo(group.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo(group.getImageUrl()),
            () -> assertThat(response.getStartDate()).isEqualTo(group.getStartDate()),
            () -> assertThat(response.getParticipantCnt()).isEqualTo(0)
        );
    }
    @Test
    public void 구역으로_모임_목록을_조회한다() {
        List<GroupCardResponse> groupCardResponses = groupRepository.findAllByDistrictOrderByCreatedDateDesc("마포", PageRequest.of(0, 10));
        assertThat(groupCardResponses.size()).isEqualTo(1);

        GroupCardResponse response = groupCardResponses.get(0);
        Assertions.assertAll(
            () -> assertThat(response.getId()).isEqualTo(group.getId()),
            () -> assertThat(response.getName()).isEqualTo(group.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo(group.getImageUrl()),
            () -> assertThat(response.getStartDate()).isEqualTo(group.getStartDate()),
            () -> assertThat(response.getParticipantCnt()).isEqualTo(0)
        );
    }
    @Test
    public void 카테고리_목록으로_모임_목록을_조회한다() {
        List<GroupCardResponse> groupCardResponses = groupRepository.findAllByCategoriesOrderByCreatedDateDesc(List.of(Category.LIFE), PageRequest.of(0, 10));
        assertThat(groupCardResponses.size()).isEqualTo(1);

        GroupCardResponse response = groupCardResponses.get(0);
        Assertions.assertAll(
            () -> assertThat(response.getId()).isEqualTo(group.getId()),
            () -> assertThat(response.getName()).isEqualTo(group.getName()),
            () -> assertThat(response.getImageUrl()).isEqualTo(group.getImageUrl()),
            () -> assertThat(response.getStartDate()).isEqualTo(group.getStartDate()),
            () -> assertThat(response.getParticipantCnt()).isEqualTo(0)
        );
    }
}
