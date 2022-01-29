package com.momo.management.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class ManagementDocumentation {

    public static RestDocumentationResultHandler findParticipationGroupCount() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("count").type(JsonFieldType.NUMBER).description("참여한 모임 수"),
        };
        return document("management/findParticipationGroupCount",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findParticipationGroups() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].imageUrl").type(JsonFieldType.STRING)
                .description("모임 대표 이미지 URL"),
            fieldWithPath("[].startDate").type(JsonFieldType.STRING)
                .description("모임 시작 날짜"),
            fieldWithPath("[].offline").type(JsonFieldType.BOOLEAN)
                .description("모임 오프라인 여부"),
            fieldWithPath("[].end").type(JsonFieldType.BOOLEAN)
                .description("모임 종료 여부"),
            fieldWithPath("[].participantCnt").type(JsonFieldType.NUMBER)
                .description("모임 참여자 수"),
        };
        return document("management/findParticipationGroups",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findParticipationGroupsSummary() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].category").type(JsonFieldType.STRING).description("모임 카테고리")
        };
        return document("management/findParticipationGroupsSummary",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findMyGroupsSummary() {
        FieldDescriptor[] response = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("모임 ID"),
            fieldWithPath("[].name").type(JsonFieldType.STRING).description("모임 이름"),
        };
        return document("management/findMyGroupsSummary",
            responseFields(response)
        );
    }

    public static RestDocumentationResultHandler findMyPosts() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("보여줄 게시글 수")
        };
        FieldDescriptor[] responsePost = new FieldDescriptor[]{
            fieldWithPath("[].groupName").type(JsonFieldType.STRING).description("모임 이름"),
            fieldWithPath("[].postCardResponse.id").type(JsonFieldType.NUMBER).description("게시글 ID"),
            fieldWithPath("[].postCardResponse.authorImage").type(JsonFieldType.STRING).description("작성자 이미지"),
            fieldWithPath("[].postCardResponse.authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("[].postCardResponse.title").type(JsonFieldType.STRING).description("게시글 제목"),
            fieldWithPath("[].postCardResponse.contents").type(JsonFieldType.STRING).description("게시글 내용"),
            fieldWithPath("[].postCardResponse.createdDate").type(JsonFieldType.STRING).description("게시글 작성일자"),
            fieldWithPath("[].postCardResponse.commentCnt").type(JsonFieldType.NUMBER).description("사용되지 않음")
        };
        return document("management/findMyPosts",
            requestParameters(requestParam),
            responseFields(responsePost)
        );
    }
}
