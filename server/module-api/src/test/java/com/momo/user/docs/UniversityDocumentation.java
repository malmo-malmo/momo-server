package com.momo.user.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class UniversityDocumentation {

    public static RestDocumentationResultHandler findByUniversityName() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("universityName").description("학교 이름")
        };
        FieldDescriptor[] responseUniversity = new FieldDescriptor[]{
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("학교 이름")
        };
        return document("user/findByUniversityName",
            requestParameters(requestParam),
            responseFields(responseUniversity)
        );
    }
}
