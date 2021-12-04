package com.momo.district;

import static com.momo.district.step.DistrictAcceptanceStep.assertThatFindCities;
import static com.momo.district.step.DistrictAcceptanceStep.assertThatFindDistricts;
import static com.momo.district.step.DistrictAcceptanceStep.requestToFindCities;
import static com.momo.district.step.DistrictAcceptanceStep.requestToFindDistricts;

import com.momo.common.acceptance.AcceptanceTest;
import com.momo.common.acceptance.step.AcceptanceStep;
import com.momo.common.dto.EnumResponse;
import com.momo.district.controller.dto.DistrictResponse;
import com.momo.district.domain.model.City;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("행정구역 통합/인수 테스트")
public class DistrictAcceptanceTest extends AcceptanceTest {

    @Test
    public void 시도_목록을_조회한다() {
        ExtractableResponse<Response> response = requestToFindCities();
        List<EnumResponse> enumResponses = getObjects(response, EnumResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindCities(enumResponses);
    }

    @Test
    public void 구군_목록을_조회한다() {
        ExtractableResponse<Response> response = requestToFindDistricts(City.SEOUL.getCode());
        List<DistrictResponse> districtResponses = getObjects(response, DistrictResponse.class);
        AcceptanceStep.assertThatStatusIsOk(response);
        assertThatFindDistricts(districtResponses);
    }
}
