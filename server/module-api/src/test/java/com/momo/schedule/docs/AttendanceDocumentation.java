package com.momo.schedule.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class AttendanceDocumentation {

    public static RestDocumentationResultHandler create() {
        FieldDescriptor[] requestAttendance = new FieldDescriptor[]{
            fieldWithPath("scheduleId").type(JsonFieldType.NUMBER).description("일정 ID"),
            fieldWithPath("attendanceCreateRequests[].participantId").type(JsonFieldType.NUMBER).description("출석체크할 사용자 ID"),
            fieldWithPath("attendanceCreateRequests[].attend").type(JsonFieldType.BOOLEAN).description("출석 체크 여부")
        };

        return document("attendance/create",
            requestFields(requestAttendance)
        );
    }

    public static RestDocumentationResultHandler update() {
        FieldDescriptor[] requestAttendance = new FieldDescriptor[]{
            fieldWithPath("scheduleId").type(JsonFieldType.NUMBER).description("일정 ID"),
            fieldWithPath("attendanceUpdateRequests[].attendanceId").type(JsonFieldType.NUMBER).description(
                "출석체크할 사용자 ID"),
            fieldWithPath("attendanceUpdateRequests[].attend").type(JsonFieldType.BOOLEAN).description("출석 체크 여부")
        };
        return document("attendance/update",
            requestFields(requestAttendance)
        );
    }

    public static RestDocumentationResultHandler findGroupAttendances() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("scheduleId").description("일정 ID"),
        };
        return document("attendance/findGroupAttendances",
            pathParameters(requestParam)
        );
    }
}