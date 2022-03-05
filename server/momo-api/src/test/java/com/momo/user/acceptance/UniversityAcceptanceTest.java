package com.momo.user.acceptance;

import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.user.acceptance.step.UniversityAcceptanceStep.requestToFind;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.user.dto.UniversityResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("커리어넷 오픈 API 통합/인수 테스트")
public class UniversityAcceptanceTest extends AcceptanceTest {

    @Test
    void 커리어넷_오픈_API로_대학교_이름을_조회한다() {
        String universityName = "한국";
        String token = getAccessToken(getUser());

        ExtractableResponse<Response> response = requestToFind(token, universityName);
        int expected = getObjects(response, UniversityResponse.class).size();

        assertThatStatusIsOk(response);
        assertThat(expected).isGreaterThan(0);
    }
}
