package com.momo.group.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class GroupDocumentation {

    public static RestDocumentationResultHandler createGroup() {
        FieldDescriptor[] requestGroup = new FieldDescriptor[]{
            fieldWithPath("name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("category").type(JsonFieldType.STRING).description("모임 카테고리"),
            fieldWithPath("isUniversity").type(JsonFieldType.BOOLEAN).description("모임 학교 여부"),
            fieldWithPath("city").type(JsonFieldType.STRING).description("모임 지역"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("모임 구역"),
            fieldWithPath("startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("recruitmentCnt").type(JsonFieldType.NUMBER).description("모임 인원수"),
            fieldWithPath("introduction").type(JsonFieldType.STRING).description("모임 설명"),
            fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("모임 이미지 주소"),
            fieldWithPath("isOffline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부")
        };

        return document("group/create",
            requestFields(requestGroup)
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
            fieldWithPath("participantCnt").type(JsonFieldType.NUMBER).description("모임 참가자 수"),
            fieldWithPath("participant").type(JsonFieldType.BOOLEAN).description("모임 참가 여부")
        };

        return document("group/find",
            pathParameters(
                parameterWithName("id").description("모임 ID")
            ),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageBySearchCondition() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("cities").description("도시 이름 목록").optional(),
            parameterWithName("categories").description("카테고리 목록").optional(),
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참가자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };

        return document("group/findPageBySearchCondition",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageByUserUniversity() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참가자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };
        return document("group/findPageByUserUniversity",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageByUserLocation() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참가자 수"),
            fieldWithPath("[].favoriteGroup").type(JsonFieldType.BOOLEAN).description("관심 모임 여부")
        };
        return document("group/findPageByUserLocation",
            requestParameters(requestSearch),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler findPageByUserCategories() {
        ParameterDescriptor[] requestSearch = new ParameterDescriptor[]{
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("페이지 사이즈")
        };
        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("모임 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER).description("모임 참가자 수"),
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
            parameterWithName("id").description("모임 ID"),
            parameterWithName("userId").description("위임할 사용자 ID")
        };
        return document("group/updateManagerByUserId",
            pathParameters(requestPathParams)
        );
    }

    public static RestDocumentationResultHandler endGroupById() {
        ParameterDescriptor[] requestPathParams = new ParameterDescriptor[]{
            parameterWithName("id").description("모임 ID")
        };
        return document("group/endGroupById",
            pathParameters(requestPathParams)
        );
    }
}
