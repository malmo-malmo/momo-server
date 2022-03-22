package com.momo.post.docs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.RequestPartDescriptor;

public class PostDocumentation {

    public static RestDocumentationResultHandler create() {
        ParameterDescriptor[] requestPost = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID"),
            parameterWithName("title").description("게시물 제목"),
            parameterWithName("contents").description("게시물 내용"),
            parameterWithName("postType").description("게시물 타입 [NORMAL, NOTICE]")
        };
        RequestPartDescriptor[] requestPart = new RequestPartDescriptor[]{
            partWithName("images").description("게시물 첨부 이미지")
        };
        FieldDescriptor[] responsePost = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시글 ID"),
            fieldWithPath("authorId").type(JsonFieldType.NUMBER).description("작성자 ID"),
            fieldWithPath("authorImage").type(JsonFieldType.STRING).description("작성자 이미지"),
            fieldWithPath("authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("게시글 내용"),
            fieldWithPath("imageUrls").type(JsonFieldType.ARRAY).description("게시글 첨부 이미지 URL"),
            fieldWithPath("createdDate").type(JsonFieldType.STRING).description("게시글 작성일자"),
        };
        return document("post/create",
            requestParameters(requestPost),
            requestParts(requestPart),
            responseFields(responsePost)
        );
    }

    public static RestDocumentationResultHandler find() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("id").description("게시물 ID"),
        };
        FieldDescriptor[] responsePost = new FieldDescriptor[]{
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시글 ID"),
            fieldWithPath("authorId").type(JsonFieldType.NUMBER).description("작성자 ID"),
            fieldWithPath("authorImage").type(JsonFieldType.STRING).description("작성자 이미지"),
            fieldWithPath("authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
            fieldWithPath("contents").type(JsonFieldType.STRING).description("게시글 내용"),
            fieldWithPath("imageUrls").type(JsonFieldType.ARRAY).description("게시글 첨부 이미지 URL"),
            fieldWithPath("createdDate").type(JsonFieldType.STRING).description("게시글 작성일자"),
        };
        return document("post/find",
            pathParameters(requestParam),
            responseFields(responsePost)
        );
    }

    public static RestDocumentationResultHandler findPageByCardsRequest() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("groupId").description("모임 ID"),
            parameterWithName("postType").description("게시글 타입"),
            parameterWithName("lastPostId").description("마지막 게시물 ID"),
            parameterWithName("size").description("보여줄 게시글 수")
        };
        FieldDescriptor[] responsePost = new FieldDescriptor[]{
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("게시글 ID"),
            fieldWithPath("[].authorImage").type(JsonFieldType.STRING).description("작성자 이미지"),
            fieldWithPath("[].authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("[].title").type(JsonFieldType.STRING).description("게시글 제목"),
            fieldWithPath("[].contents").type(JsonFieldType.STRING).description("게시글 내용"),
            fieldWithPath("[].createdDate").type(JsonFieldType.STRING).description("게시글 작성일자"),
            fieldWithPath("[].commentCnt").type(JsonFieldType.NUMBER).description("게시글 댓글 수")
        };
        return document("post/findPageByCardsRequest",
            requestParameters(requestParam),
            responseFields(responsePost)
        );
    }

    public static RestDocumentationResultHandler update() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("postId").description("수정할 게시글 ID"),
            parameterWithName("title").description("수정할 게시글 제목"),
            parameterWithName("contents").description("수정할 게시글 내용")
        };
        RequestPartDescriptor[] requestPart = new RequestPartDescriptor[]{
            partWithName("images").description("수정될 게시글 첨부 이미지")
        };
        return document("post/update",
            requestParameters(requestParam),
            requestParts(requestPart)
        );
    }

    public static RestDocumentationResultHandler delete() {
        ParameterDescriptor[] requestParam = new ParameterDescriptor[]{
            parameterWithName("postId").description("수정할 게시글 ID")
        };

        return document("post/delete",
            pathParameters(requestParam)
        );
    }
}
