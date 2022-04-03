package com.momo.chat.api.docs.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.request.ParameterDescriptor;

public class ApplyParticipantDocumentation {

    public static RestDocumentationResultHandler applyParticipant() {
        ParameterDescriptor[] path = new ParameterDescriptor[]{
            parameterWithName("chatId").description("채팅방 ID")
        };
        return document("chat/applyParticipant",
            pathParameters(path)
        );
    }
}
