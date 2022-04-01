package com.momo.user.acceptance;

import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.user.acceptance.step.UniversityAcceptanceStep.requestToFindUniversities;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.user.dto.response.UniversityResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("대학교 검색 통합/인수 테스트")
public class UniversityAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("이름으로 대학교를 검색한다")
    void searchUniversityName_success() {
        ExtractableResponse<Response> response = requestToFindUniversities(getAccessToken(getUser()), "한국");

        assertThatStatusIsOk(response);
        assertThat(getObjects(response, UniversityResponse.class).size()).isGreaterThan(0);
    }
}
