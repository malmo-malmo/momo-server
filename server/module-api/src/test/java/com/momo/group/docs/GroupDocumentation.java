package com.momo.group.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

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
}
