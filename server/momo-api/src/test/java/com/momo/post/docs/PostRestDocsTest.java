package com.momo.post.docs;

import static com.momo.PostFixture.getPostCreateRequest;
import static com.momo.PostFixture.getPostResponse;
import static com.momo.PostFixture.getPostUpdateRequest;
import static com.momo.common.CommonFileUploadSupport.generateUploadMockPutBuilder;
import static com.momo.common.CommonFileUploadSupport.uploadMockSupport;
import static com.momo.post.entity.PostType.NORMAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.post.PostController;
import com.momo.common.RestDocsControllerTest;
import com.momo.post.dto.PostCreateRequest;
import com.momo.post.dto.PostUpdateRequest;
import com.momo.post.service.impl.PostServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(PostController.class)
@DisplayName("게시글 문서화 테스트")
public class PostRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private PostController postController;

    @MockBean
    private PostServiceImpl postService;

    @Test
    void 게시글_작성() throws Exception {
        PostCreateRequest request = getPostCreateRequest(1L, NORMAL);

        given(postService.create(any(), any())).willReturn(getPostResponse());

        super.mockMvc.perform(uploadMockSupport(fileUpload("/api/post"), request))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(PostDocumentation.create());
    }

    @Test
    void 게시글_상세_조회() throws Exception {
        when(postService.findById(any(), anyLong())).thenReturn(getPostResponse());

        super.mockMvc.perform(get("/api/post/{id}", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(PostDocumentation.find());
    }

    @Test
    void 게시글_수정() throws Exception {
        PostUpdateRequest request = getPostUpdateRequest(1L);

        super.mockMvc.perform(uploadMockSupport(generateUploadMockPutBuilder(fileUpload("/api/post")), request))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(PostDocumentation.update());
    }

    @Test
    void 게시글_삭제() throws Exception {
        super.mockMvc.perform(delete("/api/post/{postId}", 1L))
            .andDo(print())
            .andExpect(status().isNoContent())
            .andDo(PostDocumentation.delete());
    }
}
