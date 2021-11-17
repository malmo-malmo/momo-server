package com.momo.group.acceptance;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
import com.momo.group.controller.dto.GroupCreateRequest;
import com.momo.group.domain.model.Category;
import com.momo.user.domain.model.Location;
import com.momo.user.domain.model.Role;
import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("모임 통합/인수 테스트")
public class GroupAcceptanceTest extends AcceptanceTest {

    @Autowired
    UserRepository userRepository;

    User manager;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        manager = userRepository.save(
            User.builder()
                .providerId("1")
                .provider(SocialProvider.KAKAO)
                .role(Role.ROLE_USER)
                .build()
        );
    }

    @Test
    @DisplayName("모임을 생성한다.")
    public void createGroup_success() {
        GroupCreateRequest req = GroupCreateRequest.builder()
            .groupName("운동 동아리")
            .categories(List.of(Category.HEALTH.name()))
            .university("한국외국어대학교")
            .location(Location.GANGDONG_GU.name())
            .startDate(LocalDate.of(2021, 11, 17))
            .endDate(LocalDate.of(2022, 11, 17))
            .recruitmentCnt(10L)
            .introduction("같이 운동하실분 모집합니다.")
            .groupImgUrl("imageUrl")
            .isOffline(true)
            .build();
        String token = createAccessToken(manager.getId());
        ExtractableResponse<Response> res = GroupAcceptanceStep.requestToCreateGroup(token, req);
        AcceptanceStep.assertThatStatusIsCreated(res);
    }

    @Test
    @DisplayName("모임 카테고리를 조회한다.")
    public void findCategories_success() {
        String token = createAccessToken(manager.getId());
        ExtractableResponse<Response> response = GroupAcceptanceStep.requestToFindCategories(token);
        AcceptanceStep.assertThatStatusIsOk(response);
        GroupAcceptanceStep.assertThatFindCategory(getObjects(response, EnumResponse.class));
    }
}
