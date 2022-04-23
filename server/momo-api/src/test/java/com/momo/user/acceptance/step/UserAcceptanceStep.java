package com.momo.user.acceptance.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.momo.user.application.dto.request.UserUpdateRequest;
import com.momo.user.application.dto.response.UserImageUpdateResponse;
import com.momo.user.application.dto.response.UserResponse;
import com.momo.user.application.dto.response.UserUpdateResponse;
import com.momo.user.domain.User;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class UserAcceptanceStep {

    public static void assertThatFindMyInformation(UserResponse response, User user) {
        Assertions.assertAll(
            () -> assertThat(response.getId()).isNotNull(),
            () -> assertThat(response.getNickname()).isEqualTo(user.getNickname()),
            () -> assertThat(response.getImageUrl()).isEqualTo(user.getImageUrl()),
            () -> assertThat(response.getCity().getCode()).isEqualTo(user.getLocation().getCity().getCode()),
            () -> assertThat(response.getCity().getName()).isEqualTo(user.getLocation().getCity().getName()),
            () -> assertThat(response.getDistrict()).isEqualTo(user.getLocation().getDistrict()),
            () -> assertThat(response.getUniversity()).isEqualTo(user.getUniversity()),
            () -> assertThat(response.getCategories().size()).isEqualTo(2)
        );
    }

    public static void assertThatUpdateMyInformation(UserUpdateResponse response, UserUpdateRequest request) {
        Assertions.assertAll(
            () -> assertThat(response.getNickname()).isEqualTo(request.getNickname()),
            () -> assertThat(response.getUniversity()).isEqualTo(request.getUniversity()),
            () -> assertThat(response.getDistrict()).isEqualTo(request.getDistrict()),
            () -> assertThat(response.getCity().getCode()).isEqualTo(request.getCity().getCode()),
            () -> assertThat(response.getCity().getName()).isEqualTo(request.getCity().getName())
        );
    }

    public static void assertThatUpdateImage(UserImageUpdateResponse response) {
        assertThat(response.getImageUrl()).isNotNull();
    }

    public static void assertThatDeleteImage(UserResponse response) {
        assertThat(response.getImageUrl()).isNull();
    }

    public static ExtractableResponse<Response> requestToFindMyInformation(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .when()
            .get("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdateMyInformation(
        String token, UserUpdateRequest userUpdateRequest
    ) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(userUpdateRequest)
            .put("/api/user")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToUpdateImage(
        String token, MultipartFile imageFile
    ) throws IOException {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            .multiPart("imageFile", imageFile.getName(), imageFile.getBytes())
            .patch("/api/user/update-image")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestToDeleteImage(String token) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .delete("/api/user/delete-image")
            .then().log().all()
            .extract();
    }
}
