package com.momo.auth.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class OAuthDocumentation {

    public static RestDocumentationResultHandler oauthLogin() {
        FieldDescriptor[] requestOAuth = new FieldDescriptor[]{
            fieldWithPath("provider").type(JsonFieldType.STRING).description("OAuth 공급자"),
            fieldWithPath("authorizationCode").type(JsonFieldType.STRING).description("인가 코드"),
            fieldWithPath("deviceCode").type(JsonFieldType.STRING).description("기기 고유번호")
        };
        FieldDescriptor[] responseOAuth = new FieldDescriptor[]{
            fieldWithPath("accessTokenType").type(JsonFieldType.STRING).description("접근용 토큰 타입"),
            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("접근용 토큰"),
            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("재발급용 토큰")
        };

        return document("oauth/oauthLogin",
            requestFields(requestOAuth),
            responseFields(responseOAuth)
        );
    }

    public static RestDocumentationResultHandler refreshLogin() {
        FieldDescriptor[] requestOAuth = new FieldDescriptor[]{
            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("재발급용 토큰"),
            fieldWithPath("deviceCode").type(JsonFieldType.STRING).description("기기 고유번호")
        };
        FieldDescriptor[] responseOAuth = new FieldDescriptor[]{
            fieldWithPath("accessTokenType").type(JsonFieldType.STRING).description("접근용 토큰 타입"),
            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("접근용 토큰"),
            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("재발급용 토큰")
        };

        return document("oauth/refreshLogin",
            requestFields(requestOAuth),
            responseFields(responseOAuth)
        );
    }
}
