package com.momo.user.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class UserDocumentation {

    public static RestDocumentationResultHandler findMyInformation() {
        FieldDescriptor[] responseUser = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("image").type(JsonFieldType.STRING).description("유저 이미지 URL"),
            fieldWithPath("city").type(JsonFieldType.STRING).description("유저 거주 도시"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("유저 거주 지역"),
            fieldWithPath("university").type(JsonFieldType.STRING).description("유저 학교"),
            fieldWithPath("categories[].code").type(JsonFieldType.STRING).description("유저 인기 카테고리 코드"),
            fieldWithPath("categories[].name").type(JsonFieldType.STRING).description("유저 인기 카테고리 이름")
        };
        return document("user/findMyInformation",
            responseFields(responseUser)
        );
    }

    public static RestDocumentationResultHandler validateDuplicateNickname() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("nickname").description("유저 닉네임")
        };
        return document("user/validateDuplicateNickname",
            requestParameters(requestParam)
        );
    }

    public static RestDocumentationResultHandler update() {
        FieldDescriptor[] requestUser = new FieldDescriptor[]{
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("university").type(JsonFieldType.STRING).description("유저 학교"),
            fieldWithPath("city").type(JsonFieldType.STRING).description("유저 거주 도시"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("유저 거주 지역")
        };
        return document("user/update",
            requestFields(requestUser)
        );
    }

    public static RestDocumentationResultHandler updateCategories() {
        FieldDescriptor[] requestUser = new FieldDescriptor[]{
            fieldWithPath("categories").type(JsonFieldType.ARRAY).description("관심 카테고리 목록"),
        };
        return document("user/updateCategories",
            requestFields(requestUser)
        );
    }
}
