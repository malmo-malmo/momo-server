package com.momo.post.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

public class CommentDocumentation {

    public static RestDocumentationResultHandler create() {
        FieldDescriptor[] requestComment = new FieldDescriptor[]{
            fieldWithPath("postId").type(JsonFieldType.NUMBER).description("게시물 ID"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("댓글 내용")
        };
        FieldDescriptor[] responseComment = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("댓글 ID"),
            fieldWithPath("authorId").type(JsonFieldType.NUMBER).description("작성자 ID"),
            fieldWithPath("authorImage").type(JsonFieldType.STRING).description("작성자 이미지"),
            fieldWithPath("authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("댓글 내용"),
            fieldWithPath("createdDate").type(JsonFieldType.STRING).description("댓글 작성일자")
        };
        return document("comment/create",
            requestFields(requestComment),
            responseFields(responseComment)
        );
    }

    public static RestDocumentationResultHandler findPageByPost() {
        ParameterDescriptor[] requestComment = new ParameterDescriptor[]{
            parameterWithName("postId").description("게시글 ID"),
            parameterWithName("page").description("페이지 번호"),
            parameterWithName("size").description("보여줄 게시글 수")
        };

        FieldDescriptor[] responseComment = new FieldDescriptor[]{
            fieldWithPath("commentResponses[].id").type(JsonFieldType.NUMBER).description("댓글 ID"),
            fieldWithPath("commentResponses[].authorId").type(JsonFieldType.NUMBER).description("작성자 ID"),
            fieldWithPath("commentResponses[].authorImage").type(JsonFieldType.STRING).description("작성자 이미지"),
            fieldWithPath("commentResponses[].authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("commentResponses[].contents").type(JsonFieldType.STRING).description("댓글 내용"),
            fieldWithPath("commentResponses[].createdDate").type(JsonFieldType.STRING).description("댓글 작성일자"),
            fieldWithPath("commentCnt").type(JsonFieldType.NUMBER).description("댓글 수")
        };
        return document("comment/findPageByPost",
            requestParameters(requestComment),
            responseFields(responseComment)
        );
    }
}
