package com.momo.chat.api.docs.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class FindChatMessagesDocumentation {

    public static RestDocumentationResultHandler findChatMessages() {
        ParameterDescriptor[] requestParams = new ParameterDescriptor[]{
            parameterWithName("chatId").description("채팅방 ID")
        };
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].message").type(JsonFieldType.STRING).description("내용"),
            fieldWithPath("[].creDatetime").type(JsonFieldType.STRING).description("작성 일자"),
            fieldWithPath("[].username").type(JsonFieldType.STRING).description("작성자"),
            fieldWithPath("[].profileImageUrl").type(JsonFieldType.STRING).description(
                "작성자 프로필 이미지 URL"),
            fieldWithPath("[].messageType").type(JsonFieldType.STRING).description("메시지 타입"),
            fieldWithPath("[].system").type(JsonFieldType.BOOLEAN).description("시스템 메시지 여부")
        };
        return document("chat/findChatMessages",
            pathParameters(requestParams),
            responseFields(response)
        );
    }
}
