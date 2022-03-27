package com.momo.chat.api.docs.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

public class FindChatsDocumentation {

    public static RestDocumentationResultHandler findChats() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].chatId").type(JsonFieldType.NUMBER).description("채팅방 ID"),
            fieldWithPath("[].profileImageUrl").type(JsonFieldType.STRING).description(
                "마지막 메시지 작성자 프로필 URL"),
            fieldWithPath("[].username").type(JsonFieldType.STRING).description("마지막 메시지 작성자 이름"),
            fieldWithPath("[].lastMessage").type(JsonFieldType.STRING).description(
                "마지막 메시지 작성 내용"),
            fieldWithPath("[].createDate").type(JsonFieldType.STRING).description("마지막 메시지 작성 일자"),
            fieldWithPath("[].createDateMessage").type(JsonFieldType.STRING).description(
                "마지막 메시지 가공된 날짜명")
        };
        return document("chat/findChats",
            responseFields(response)
        );
    }
}
