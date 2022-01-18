package com.momo.user.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.RequestPartDescriptor;

public class UserDocumentation {

    public static RestDocumentationResultHandler createFavoriteGroup() {
        FieldDescriptor[] request = new FieldDescriptor[]{
            fieldWithPath("groupId").type(JsonFieldType.NUMBER).description("모임 ID")
        };
        return document("user/createFavoriteGroup",
            requestFields(request)
        );
    }

    public static RestDocumentationResultHandler findMyInformation() {
        FieldDescriptor[] responseUser = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("image").type(JsonFieldType.STRING).description("유저 이미지 URL"),
            fieldWithPath("city.code").type(JsonFieldType.STRING).description("유저 거주 도시 코드"),
            fieldWithPath("city.name").type(JsonFieldType.STRING).description("유저 거주 도시 이름"),
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

    public static RestDocumentationResultHandler findFavoriteGroupCount() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("count").type(JsonFieldType.NUMBER).description("관심 모임 수"),
        };
        return document("user/findFavoriteGroupCount",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findFavoriteGroups() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("관심 모임 ID"),
            fieldWithPath("[].groupCardResponse.id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].groupCardResponse.name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].groupCardResponse.imageUrl").type(JsonFieldType.STRING).description("모임 대표 이미지 URL"),
            fieldWithPath("[].groupCardResponse.startDate").type(JsonFieldType.STRING).description("모임 시작 날짜"),
            fieldWithPath("[].groupCardResponse.offline").type(JsonFieldType.BOOLEAN).description("모임 오프라인 여부"),
            fieldWithPath("[].groupCardResponse.participantCnt").type(JsonFieldType.NUMBER).description("모임 참여자 수"),
            fieldWithPath("[].groupCardResponse.favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };
        return document("user/findFavoriteGroups",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findFavoriteCategories() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].code").type(JsonFieldType.STRING).description("관심 카테고리 코드"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("관심 카테고리 이름")
        };
        return document("user/findFavoriteCategories",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findParticipatingGroupCount() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("count").type(JsonFieldType.NUMBER).description("참여한 모임 수"),
        };
        return document("user/findParticipatingGroupCount",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findParticipatingGroups() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING)
                .description("모임 대표 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING)
                .description("모임 시작 날짜"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN)
                .description("모임 오프라인 여부"),
            fieldWithPath("[].end").type(JsonFieldType.BOOLEAN)
                .description("모임 종료 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER)
                .description("모임 참여자 수"),
        };
        return document("user/findParticipatingGroups",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler updateMyInformation() {
        ParameterDescriptor[] requestUser = new ParameterDescriptor[]{
            parameterWithName("nickname").description("유저 닉네임"),
            parameterWithName("university").description("유저 학교"),
            parameterWithName("city").description("유저 거주 도시"),
            parameterWithName("district").description("유저 거주 지역")
        };
        RequestPartDescriptor[] requestPart = new RequestPartDescriptor[]{
            partWithName("image").description("유저 프로필 이미지")
        };
        return document("user/updateMyInformation",
            requestParameters(requestUser),
            requestParts(requestPart)
        );
    }

    public static RestDocumentationResultHandler updateFavoriteCategories() {
        FieldDescriptor[] requestUser = new FieldDescriptor[]{
            fieldWithPath("favoriteCategories").type(JsonFieldType.ARRAY).description("관심 카테고리 목록"),
        };
        return document("user/updateFavoriteCategories",
            requestFields(requestUser)
        );
    }

    public static RestDocumentationResultHandler deleteFavoriteGroup() {
        return document("user/deleteFavoriteGroup",
            pathParameters(
                parameterWithName("id").description("모임 ID")
            )
        );
    }
}
