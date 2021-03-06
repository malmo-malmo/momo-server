package com.momo.group.docs;

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

public class GroupDocumentation {

    public static RestDocumentationResultHandler createGroup() {
        ParameterDescriptor[] requestGroup = new ParameterDescriptor[]{
            parameterWithName("name").description("모임 이름"),
            parameterWithName("category").description("모임 카테고리"),
            parameterWithName("university").description("모임 대학교").optional(),
            parameterWithName("city").description("모임 지역"),
            parameterWithName("district").description("모임 구역"),
            parameterWithName("startDate").description("모임 시작일자"),
            parameterWithName("recruitmentCnt").description("모임 인원수"),
            parameterWithName("introduction").description("모임 설명"),
            parameterWithName("isOffline").description("모임 온/오프라인 여부")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL")
        };

        return document("group/create",
            requestParameters(requestGroup),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findGroup() {
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("managerId").type(JsonFieldType.NUMBER).description("모임 담당자 ID"),
            fieldWithPath("name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("university").type(JsonFieldType.STRING).description("모임 학교"),
            fieldWithPath("city").type(JsonFieldType.STRING).description("모임 도시"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("모임 도시 상세"),
            fieldWithPath("offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("introduction").type(JsonFieldType.STRING).description("모임 설명"),
            fieldWithPath("recruitmentCnt").type(JsonFieldType.NUMBER).description("모임 인원수"),
            fieldWithPath("end").type(JsonFieldType.BOOLEAN).description("모임 종료 여부"),
            fieldWithPath("participantCnt").type(JsonFieldType.NUMBER).description("모임 참여자 수"),
            fieldWithPath("participant").type(JsonFieldType.BOOLEAN).description("모임 참가 여부")
        };

        return document("group/find",
            pathParameters(
                parameterWithName("groupId").description("모임 ID")
            ),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageBySearchConditionV1() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("groupName").description("모임 이름(키워드)").optional(),
            parameterWithName("cities").description("도시 이름 목록"),
            parameterWithName("categories").description("카테고리 목록"),
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참여자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };

        return document("group/findPageBySearchConditionV1",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageBySearchConditionV2() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("groupName").description("모임 이름(키워드)").optional(),
            parameterWithName("cities").description("도시 이름 목록"),
            parameterWithName("categories").description("카테고리 목록"),
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참여자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };

        return document("group/findPageBySearchConditionV2",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageByUserUniversity() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("lastGroupId").description("페이지의 마지막 모임 ID(첫 페이지 null)").optional(),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참여자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };
        return document("group/findPageByUserUniversity",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageByUserLocation() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("lastGroupId").description("페이지의 마지막 모임 ID(첫 페이지 null)").optional(),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참여자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };
        return document("group/findPageByUserLocation",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageByUserCategories() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("lastGroupId").description("페이지의 마지막 모임 ID(첫 페이지 null)").optional(),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참여자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };
        return document("group/findPageByUserCategories",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findGroupCategories() {
        FieldDescriptor[] responseCategories = new FieldDescriptor[]{
            fieldWithPath("[].code").type(JsonFieldType.STRING).description("카테고리 코드"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("카테고리 이름")
        };
        return document("group/findGroupCategories",
            responseFields(responseCategories)
        );
    }

    public static RestDocumentationResultHandler updateManagerByUserId() {
        ParameterDescriptor[] requestPathParams = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID"),
            parameterWithName("userId").description("위임할 사용자 ID")
        };
        return document("group/updateManagerByUserId",
            pathParameters(requestPathParams)
        );
    }

    public static RestDocumentationResultHandler endGroup() {
        ParameterDescriptor[] requestPathParams = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID")
        };
        return document("group/endGroup",
            pathParameters(requestPathParams)
        );
    }

    public static RestDocumentationResultHandler updateGroupInformation() {
        FieldDescriptor[] request = new FieldDescriptor[]{
            fieldWithPath("id").description("모임 ID"),
            fieldWithPath("name").description("모임 이름"),
            fieldWithPath("category").description("모임 카테고리"),
            fieldWithPath("university").description("모임 대학교").optional(),
            fieldWithPath("city").description("모임 도시"),
            fieldWithPath("district").description("모임 도시 상세"),
            fieldWithPath("recruitmentCnt").description("모임 모집 인원"),
            fieldWithPath("introduction").description("모임 소개"),
            fieldWithPath("isOffline").description("모임 온/오프라인 여부")
        };
        return document("group/updateGroupInformation",
            requestFields(request)
        );
    }

    public static RestDocumentationResultHandler updateImage() {
        RequestPartDescriptor[] requestPart = new RequestPartDescriptor[]{
            partWithName("imageFile").description("모임 대표 이미지")
        };
        ParameterDescriptor[] requestPathParams = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID")
        };
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("이미지 URL")
        };
        return document("group/updateImage",
            requestParts(requestPart),
            pathParameters(requestPathParams),
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler deleteImage() {
        ParameterDescriptor[] requestPathParams = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID")
        };

        return document("group/deleteImage",
            pathParameters(requestPathParams)
        );
    }
}
