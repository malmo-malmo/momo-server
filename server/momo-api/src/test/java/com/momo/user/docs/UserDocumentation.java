package com.momo.user.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.RequestPartDescriptor;

public class UserDocumentation {

    public static RestDocumentationResultHandler findMyInformation() {
        FieldDescriptor[] responseUser = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("유저 이미지 URL"),
            fieldWithPath("city.code").type(JsonFieldType.STRING).description("유저 거주 도시 코드"),
            fieldWithPath("city.name").type(JsonFieldType.STRING).description("유저 거주 도시 이름"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("유저 거주 지역"),
            fieldWithPath("university").type(JsonFieldType.STRING).description("유저 학교"),
            fieldWithPath("categories[].code").type(JsonFieldType.STRING).description("유저 인기 카테고리 코드").optional(),
            fieldWithPath("categories[].name").type(JsonFieldType.STRING).description("유저 인기 카테고리 이름").optional()
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

    public static RestDocumentationResultHandler updateMyInformation() {
        FieldDescriptor[] request = new FieldDescriptor[]{
            fieldWithPath("nickname").description("유저 닉네임"),
            fieldWithPath("university").description("유저 학교"),
            fieldWithPath("city").description("유저 거주 도시"),
            fieldWithPath("district").description("유저 거주 지역")
        };
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("university").type(JsonFieldType.STRING).description("유저 학교"),
            fieldWithPath("city.code").type(JsonFieldType.STRING).description("유저 거주 도시 코드"),
            fieldWithPath("city.name").type(JsonFieldType.STRING).description("유저 거주 도시 이름"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("유저 거주 지역"),
        };
        return document("user/updateMyInformation",
            requestFields(request),
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler updateImageWithImage() {
        RequestPartDescriptor[] requestPart = new RequestPartDescriptor[]{
            partWithName("imageFile").description("유저 프로필 이미지")
        };
        return document("user/updateImage",
            requestParts(requestPart)
        );
    }
}
