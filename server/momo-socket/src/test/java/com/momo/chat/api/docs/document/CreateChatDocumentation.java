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

public class CreateChatDocumentation {

    public static RestDocumentationResultHandler createChat() {
        ParameterDescriptor[] path = new ParameterDescriptor[]{
            parameterWithName("groupId").description("채팅방 ID")
        };
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("chatId").type(JsonFieldType.NUMBER).description("생성된 채팅방 ID")
        };
        return document("chat/createChat",
            pathParameters(path),
            responseFields(response)
        );
    }
}
