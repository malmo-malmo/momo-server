package com.momo.schedule.docs;

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

public class ScheduleDocumentation {

    public static RestDocumentationResultHandler create() {
        FieldDescriptor[] requestSchedule = new FieldDescriptor[]{
            fieldWithPath("groupId").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("title").type(JsonFieldType.STRING).description("일정 이름"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("일정 내용"),
            fieldWithPath("isOffline").type(JsonFieldType.BOOLEAN).description("온/오프라인 여부"),
            fieldWithPath("startDateTime").type(JsonFieldType.STRING).description("일정 시작일자")
        };
        FieldDescriptor[] responseSchedule = new FieldDescriptor[]{
            fieldWithPath("scheduleId").type(JsonFieldType.NUMBER).description("일정 ID"),
            fieldWithPath("authorImage").type(JsonFieldType.STRING).description("작성자 이미지"),
            fieldWithPath("authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("title").type(JsonFieldType.STRING).description("일정 제목"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("일정 내용"),
            fieldWithPath("offline").type(JsonFieldType.BOOLEAN).description("오프라인 여부"),
            fieldWithPath("startDateTime").type(JsonFieldType.STRING).description("일정 시작일자"),
            fieldWithPath("attendanceCheck").type(JsonFieldType.BOOLEAN).description("출석 체크"),
            fieldWithPath("attend").type(JsonFieldType.BOOLEAN).description("출석 여부"),
        };

        return document("schedule/create",
            requestFields(requestSchedule),
            responseFields(responseSchedule)
        );
    }

    public static RestDocumentationResultHandler findPageByGroup() {
        ParameterDescriptor[] requestSchedule = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID"),
            parameterWithName("page").description("페이지"),
            parameterWithName("size").description("사이즈")
        };
        FieldDescriptor[] responseSchedule = new FieldDescriptor[]{
            fieldWithPath("groupScheduleResponses[].scheduleId").type(JsonFieldType.NUMBER).description("일정 ID"),
            fieldWithPath("groupScheduleResponses[].authorImage").type(JsonFieldType.STRING).description("일정 작성자 이미지"),
            fieldWithPath("groupScheduleResponses[].authorNickname").type(JsonFieldType.STRING).description(
                "일정 작성자 닉네임"),
            fieldWithPath("groupScheduleResponses[].title").type(JsonFieldType.STRING).description("일정 이름"),
            fieldWithPath("groupScheduleResponses[].contents").type(JsonFieldType.STRING).description("일정 내용"),
            fieldWithPath("groupScheduleResponses[].offline").type(JsonFieldType.BOOLEAN).description("일정 온/오프라인 여부"),
            fieldWithPath("groupScheduleResponses[].startDateTime").type(JsonFieldType.STRING).description("일정 시작일자"),
            fieldWithPath("groupScheduleResponses[].attendanceCheck").type(JsonFieldType.BOOLEAN).description("출석체크"),
            fieldWithPath("groupScheduleResponses[].attend").type(JsonFieldType.BOOLEAN).description("출석 여부"),
            fieldWithPath("managerId").type(JsonFieldType.NUMBER).description("담당자 ID")
        };

        return document("schedule/findPageByGroup",
            requestParameters(requestSchedule),
            responseFields(responseSchedule)
        );
    }

    public static RestDocumentationResultHandler findPageByUser() {
        ParameterDescriptor[] requestSchedule = new ParameterDescriptor[]{
            parameterWithName("searchStartDate").description("일정 시작일자"),
            parameterWithName("searchEndDate").description("일정 종료일자")
        };
        FieldDescriptor[] responseSchedule = new FieldDescriptor[]{
            fieldWithPath("[].groupId").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].groupCategory.code").type(JsonFieldType.STRING).description("모임 타입 코드"),
            fieldWithPath("[].groupCategory.name").type(JsonFieldType.STRING).description("모임 타입 이름"),
            fieldWithPath("[].title").type(JsonFieldType.STRING).description("일정 이름"),
            fieldWithPath("[].startDateTime").type(JsonFieldType.STRING).description("일정 시작일자")
        };

        return document("schedule/findPageByUser",
            requestParameters(requestSchedule),
            responseFields(responseSchedule)
        );
    }
}
