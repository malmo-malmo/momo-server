package com.momo.user.acceptance;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.user.acceptance.step.UniversityAcceptanceStep;
import com.momo.user.controller.dto.UniversityResponse;
import com.momo.user.domain.model.Role;
import com.momo.user.domain.model.SocialProvider;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("커리어넷 오픈 API 통합/인수 테스트")
public class UniversityAcceptanceTest extends AcceptanceTest {

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
    @DisplayName("커리어넷 오픈 API로 대학교 이름을 조회한다.")
    public void update_success() {
        String universityName = "한국";
        String token = createAccessToken(user.getId());
        ExtractableResponse<Response> res = UniversityAcceptanceStep.requestToFind(token,
            universityName);
        AcceptanceStep.assertThatStatusIsOk(res);
        Assertions.assertThat(getObjects(res, UniversityResponse.class).size()).isGreaterThan(0);
    }
}
