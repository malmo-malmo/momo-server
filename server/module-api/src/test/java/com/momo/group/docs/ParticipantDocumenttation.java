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

public class ParticipantDocumenttation {

    public static RestDocumentationResultHandler applyParticipantByGroup() {
        FieldDescriptor[] requestGroup = new FieldDescriptor[]{
            fieldWithPath("groupId").type(JsonFieldType.NUMBER).description("모임 ID")
        };

        return document("group/applyParticipantByGroup",
            requestFields(requestGroup)
        );
    }

    public static RestDocumentationResultHandler findByGroup() {
        ParameterDescriptor[] requestGroup = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID")
        };

        FieldDescriptor[] responseGroup = new FieldDescriptor[]{
            fieldWithPath("[].userId").type(JsonFieldType.NUMBER).description("사용자 ID"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING).description("이미지 URL"),
            fieldWithPath("[].nickname").type(JsonFieldType.STRING).description("사용자 닉네임"),
            fieldWithPath("[].attendanceRate").type(JsonFieldType.NUMBER).description("사용자 출석률"),
        };

        return document("group/applyParticipantByGroup",
            requestParameters(requestGroup),
            responseFields(responseGroup)
        );
    }

    public static RestDocumentationResultHandler delete() {
        ParameterDescriptor[] requestGroup = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID")
        };
        return document("group/delete",
            pathParameters(requestGroup)
        );
    }
}
