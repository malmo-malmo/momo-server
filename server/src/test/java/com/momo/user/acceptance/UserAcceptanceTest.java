package com.momo.user.acceptance;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.common.dto.GroupCategoryRequest;
import com.momo.user.acceptance.step.UserAcceptanceStep;
import com.momo.user.controller.dto.UserUpdateRequest;
import com.momo.user.domain.model.Role;
import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("유저 통합/인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        user = userRepository.save(
            User.builder()
                .nickname("말모말모")
                .providerId("1")
                .provider(SocialProvider.KAKAO)
                .role(Role.ROLE_USER)
                .build()
        );
    }

    @Test
    @DisplayName("유저 정보를 수정한다.")
    public void update_success() {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .nickname("모모")
            .university("한국대학교")
            .location("GURO_GU")
            .build();
        String token = createAccessToken(user.getId());
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdate(token, request);
        AcceptanceStep.assertThatStatusIsOk(res);
    }

    @Test
    @DisplayName("유저 정보를 수정할 때 잘못된 ENUM 값을 보내면 실패한다.")
    public void update_fail() {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .university("한국대학교")
            .location("ABC")
            .build();
        String token = createAccessToken(user.getId());
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdate(token, request);
        AcceptanceStep.assertThatStatusIsBadRequest(res);
    }

    @Test
    @DisplayName("유저 정보를 수정할 때 입력값이 공백 또는 널이면 실패한다.")
    public void update_blank_fail() {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .nickname(" ")
            .build();
        String token = createAccessToken(user.getId());
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdate(token, request);
        AcceptanceStep.assertThatStatusIsBadRequest(res);
    }

    @Test
    @DisplayName("유저가 관심 카테고리를 수정한다.")
    public void updateGroupCategories_success() {
        GroupCategoryRequest req = new GroupCategoryRequest(
            List.of("HEALTH", "EMPLOYMENT", "HOBBY"));
        String token = createAccessToken(user.getId());
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdateGroupCategory(token,
            req);
        AcceptanceStep.assertThatStatusIsOk(res);
    }

    @Test
    @DisplayName("유저 관심 카테고리를 수정할 때 잘못된 ENUM 값을 보내면 실패한다.")
    public void updateGroupCategories_fail() {
        GroupCategoryRequest req = new GroupCategoryRequest(
            List.of("HEALTH", "EMPLOYMENT", "HOBB"));
        String token = createAccessToken(user.getId());
        ExtractableResponse<Response> res = UserAcceptanceStep.requestToUpdateGroupCategory(token,
            req);
        AcceptanceStep.assertThatStatusIsBadRequest(res);
    }

    @Test
    @DisplayName("저장 가능한 지역들을 조회한다.")
    public void findLocations_success() {
        String token = createAccessToken(user.getId());
        ExtractableResponse<Response> response = UserAcceptanceStep.requestToLocations(token);
        AcceptanceStep.assertThatStatusIsOk(response);
        UserAcceptanceStep.assertThatFindLocations(getObjects(response, EnumResponse.class));
    }
}
