package com.momo.management.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class ManagementDocumentation {

    public static RestDocumentationResultHandler findParticipatingGroupCount() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("count").type(JsonFieldType.NUMBER).description("참여한 모임 수"),
        };
        return document("management/findParticipatingGroupCount",
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
        return document("management/findParticipatingGroups",
            responseFields(response)
        );
    }
}
