package com.momo.schedule.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class AttendanceDocumentation {

    public static RestDocumentationResultHandler create() {
        FieldDescriptor[] requestAttendance = new FieldDescriptor[]{
            fieldWithPath("groupId").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("scheduleId").type(JsonFieldType.NUMBER).description("일정 ID"),
            fieldWithPath("attendanceCreateRequests[].userId").type(JsonFieldType.NUMBER).description("출석체크할 사용자 ID"),
            fieldWithPath("attendanceCreateRequests[].isAttend").type(JsonFieldType.BOOLEAN).description("출석 체크 여부")
        };

        return document("schedule/attendance",
            requestFields(requestAttendance)
        );
    }
}