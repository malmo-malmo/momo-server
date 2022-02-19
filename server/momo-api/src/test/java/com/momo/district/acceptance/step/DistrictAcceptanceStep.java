package com.momo.district.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.district.dto.DistrictResponse;
import com.momo.domain.district.entity.City;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import org.springframework.http.HttpHeaders;

public class DistrictAcceptanceStep {

    public static void assertThatFindCities(List<EnumResponse> responses) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isEqualTo(City.values().length)
        );
    }

    public static void assertThatFindDistricts(List<DistrictResponse> responses) {
        Assertions.assertAll(
            () -> assertThat(responses.size()).isNotNull()
        );
    }

    public static ExtractableResponse<Response> requestToFindCities(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/district/cities")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToFindDistricts(String token, String cityCode) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .queryParam("cityCode", cityCode)
            .when()
            .get("/api/district/districts")
            .then().log().all()
            .extract();
    }
}
