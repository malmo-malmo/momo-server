package com.momo.chat.api.acceptance.step;

import static io.restassured.RestAssured.given;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ChatAcceptanceStep {

    public static ExtractableResponse<Response> requestCreateChat(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("groupId", groupId)
            .post("/api/chat/group/{groupId}")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestApplyGroup(String token, Long chatId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("chatId", chatId)
            .post("/api/group/apply-participant/{chatId}")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestChatMessageHistory(String token,
        Long chatId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("chatId", chatId)
            .get("/api/chat/{chatId}/messages")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> requestChats(String token, Long groupId) {
        return given().log().all()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .get("/api/chat/list")
            .then().log().all()
            .extract();
    }
}
