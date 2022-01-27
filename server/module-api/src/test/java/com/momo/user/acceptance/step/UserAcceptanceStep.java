package com.momo.user.acceptance.step;

import static com.momo.CommonFileUploadSupport.uploadAssuredSupport;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.domain.user.dto.UserResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.dto.UserUpdateResponse;
import com.momo.domain.user.entity.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UserAcceptanceStep {

    public static void assertThatFindMyInformation(UserResponse response, User user) {
        Assertions.assertAll(
            () -> assertThat(response.getId()).isNotNull(),
            () -> assertThat(response.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(response.getImage()).isEqualTo(user.getImageUrl()),
            () -> assertThat(response.getCity().getCode()).isEqualTo(user.getCity().getCode()),
            () -> assertThat(response.getCity().getName()).isEqualTo(user.getCity().getName()),
            () -> assertThat(response.getDistrict()).isEqualTo(user.getDistrict()),
            () -> assertThat(response.getUniversity()).isEqualTo(user.getUniversity()),
            () -> assertThat(response.getCategories().size()).isEqualTo(2)
        );
    }

    public static void assertThatUpdateMyInformation(UserUpdateResponse response, UserUpdateRequest request) {
        Assertions.assertAll(
            () -> assertThat(response.getImageUrl()).isNull(),
            () -> assertThat(response.getCity()).isNotNull(),
            () -> assertThat(response.getNickname()).isEqualTo(request.getNickname()),
            () -> assertThat(response.getUniversity()).isEqualTo(request.getUniversity()),
            () -> assertThat(response.getDistrict()).isEqualTo(request.getDistrict())
        );
    }

    public static void assertThatUpdateMyInformationWithImage(UserUpdateResponse response, UserUpdateRequest request) {
        Assertions.assertAll(
            () -> assertThat(response.getImageUrl()).isNotNull(),
            () -> assertThat(response.getCity()).isNotNull(),
            () -> assertThat(response.getNickname()).isEqualTo(request.getNickname()),
            () -> assertThat(response.getUniversity()).isEqualTo(request.getUniversity()),
            () -> assertThat(response.getDistrict()).isEqualTo(request.getDistrict())
        );
    }

    public static ExtractableResponse<Response> requestToFindMyInformation(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdateMyInformation(String token,
        UserUpdateRequest userUpdateRequest) {
        return uploadAssuredSupport(given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token), userUpdateRequest)
            .put("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdateMyInformationWithImage(String token,
        UserUpdateRequest userUpdateRequest) {
        return uploadAssuredSupport(given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE), userUpdateRequest)
            .put("/api/user")
            .then().log().all()
            .extract();
    }
}
