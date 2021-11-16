package com.momo.group.acceptance;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.group.acceptance.step.GroupAcceptanceStep;
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
  @DisplayName("모임 카테고리를 조회한다.")
  public void findCategories_success() {
    String token = createAccessToken(manager.getId());
    ExtractableResponse<Response> response = GroupAcceptanceStep.requestToFindCategories(token);
    List<EnumResponse> enumResponses = getObjects(response, EnumResponse.class);
    AcceptanceStep.assertThatStatusIsOk(response);
    GroupAcceptanceStep.assertThatFindCategory(enumResponses);
  }
}
