package com.momo.district.acceptance;

import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.assertThatFindCities;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.assertThatFindDistricts;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.requestToFindCities;
import static com.momo.district.acceptance.step.DistrictAcceptanceStep.requestToFindDistricts;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.district.dto.DistrictResponse;
import com.momo.domain.district.entity.City;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("행정구역 통합/인수 테스트")
public class DistrictAcceptanceTest extends AcceptanceTest {

    @Test
    public void 시도_목록을_조회한다() {
        ExtractableResponse<Response> response = requestToFindCities();

        assertThatStatusIsOk(response);
        assertThatFindCities(getObjects(response, EnumResponse.class));
    }

    @Test
    public void 구군_목록을_조회한다() {
        ExtractableResponse<Response> response = requestToFindDistricts(City.SEOUL.getCode());

        assertThatStatusIsOk(response);
        assertThatFindDistricts(getObjects(response, DistrictResponse.class));
    }
}
