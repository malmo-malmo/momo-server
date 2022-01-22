package com.momo.user.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class FavoriteDocumentation {

    public static RestDocumentationResultHandler createFavoriteGroup() {
        FieldDescriptor[] request = new FieldDescriptor[]{
            fieldWithPath("groupId").type(JsonFieldType.NUMBER).description("모임 ID")
        };
        return document("favorite/createFavoriteGroup",
            requestFields(request)
        );
    }

    public static RestDocumentationResultHandler findFavoriteGroupCount() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("count").type(JsonFieldType.NUMBER).description("관심 모임 수"),
        };
        return document("favorite/findFavoriteGroupCount",
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
        return document("favorite/findFavoriteGroups",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findFavoriteCategories() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].code").type(JsonFieldType.STRING).description("관심 카테고리 코드"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("관심 카테고리 이름")
        };
        return document("favorite/findFavoriteCategories",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler updateFavoriteCategories() {
        FieldDescriptor[] requestUser = new FieldDescriptor[]{
            fieldWithPath("favoriteCategories").type(JsonFieldType.ARRAY).description("관심 카테고리 목록"),
        };
        return document("favorite/updateFavoriteCategories",
            requestFields(requestUser)
        );
    }

    public static RestDocumentationResultHandler deleteFavoriteGroup() {
        return document("favorite/deleteFavoriteGroup",
            pathParameters(
                parameterWithName("id").description("모임 ID")
            )
        );
    }
}
