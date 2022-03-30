package com.momo.district.acceptance;

import static com.momo.UserFixture.getUser;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.assertThatFindCities;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.assertThatFindDistricts;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.requestToFindCities;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.requestToFindDistricts;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.dto.EnumResponse;
import com.momo.district.dto.DistrictResponse;
import com.momo.district.entity.City;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("행정구역 통합/인수 테스트")
public class DistrictAcceptanceTest extends AcceptanceTest {

    private User user;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
        user = getUser();
    }

    @Test
    public void 시도_목록을_조회한다() {
        String token = getAccessToken(user);
        ExtractableResponse<Response> response = requestToFindCities(token);

        assertThatStatusIsOk(response);
        assertThatFindCities(getObjects(response, EnumResponse.class));
    }

    @Test
    public void 구군_목록을_조회한다() {
        String token = getAccessToken(user);
        ExtractableResponse<Response> response = requestToFindDistricts(token, City.SEOUL.getCode());

        assertThatStatusIsOk(response);
        assertThatFindDistricts(getObjects(response, DistrictResponse.class));
    }
}
