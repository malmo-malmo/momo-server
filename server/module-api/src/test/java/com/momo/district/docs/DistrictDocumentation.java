package com.momo.district.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class DistrictDocumentation {

    public static RestDocumentationResultHandler findCities() {
        FieldDescriptor[] responseCategories = new FieldDescriptor[]{
            fieldWithPath("[].code").type(JsonFieldType.STRING).description("카테고리 코드"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("카테고리 이름")
        };
        return document("district/findCities",
            responseFields(responseCategories));
    }

    public static RestDocumentationResultHandler findDistrictsByCity() {
        ParameterDescriptor[] requestParams = new ParameterDescriptor[]{
            parameterWithName("cityCode").description("도시 코드")
        };
        FieldDescriptor[] responseDistricts = new FieldDescriptor[]{
            fieldWithPath("[].districtName").type(JsonFieldType.STRING).description("구역 이름")
        };
        return document("district/findDistrictsByCity",
            requestParameters(requestParams),
            responseFields(responseDistricts)
        );
    }
}
