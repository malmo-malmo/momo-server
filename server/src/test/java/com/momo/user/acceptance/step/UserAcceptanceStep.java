package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.common.dto.EnumResponse;
import com.momo.group.controller.dto.CategoryRequest;
import com.momo.user.controller.dto.UserUpdateRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UserAcceptanceStep {

    public static void assertThatFindLocations(List<EnumResponse> responses) {
        assertThat(EnumResponse.listOfLocation().size()).isEqualTo(responses.size());
    }

    public static ExtractableResponse<Response> requestToUpdate(String token,
        UserUpdateRequest userUpdateRequest) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(userUpdateRequest)
            .when()
            .patch("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdateCategory(String token,
        CategoryRequest request) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .patch("/api/user/categories")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToLocations(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/user/locations")
            .then().log().all()
            .extract();
    }
}
