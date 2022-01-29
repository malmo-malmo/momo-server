package com.momo.post.docs;

import static com.momo.common.CommonFileUploadSupport.generateUploadMockPutBuilder;
import static com.momo.common.CommonFileUploadSupport.uploadMockSupport;
import static com.momo.common.CommonFileUploadSupport.uploadTestFile;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.common.RestDocsControllerTest;
import com.momo.api.post.PostController;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.dto.PostUpdateRequest;
import com.momo.domain.post.service.impl.PostServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
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
        given(postService.create(any(), any())).willReturn(PostResponse.builder()
            .id(1L)
            .authorId(1L)
            .authorImage("작성자 이미지")
            .authorNickname("작성자 닉네임")
            .title("게시글 제목")
            .contents("게시글 내용")
            .imageUrls(List.of("게시글 첨부 이미지 URL"))
            .createdDate(LocalDateTime.now())
            .build());
        PostCreateRequest request = PostCreateRequest.builder()
            .groupId(1L)
            .title("테스트 게시글")
            .contents("테스트 게시글 내용")
            .typeName("테스트 타입")
            .images(List.of(uploadTestFile))
            .build();
        super.mockMvc.perform(uploadMockSupport(fileUpload("/api/post"), request))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(PostDocumentation.create());
    }

    @Test
    void 게시글_상세_조회() throws Exception {
        when(postService.findById(any(), anyLong())).thenReturn(PostResponse.builder()
            .id(1L)
            .authorId(1L)
            .authorImage("http://~~")
            .authorNickname("테스트맨")
            .title("테스트 게시글 제목")
            .contents("테스트 게시글 내용")
            .imageUrls(List.of("http://~~"))
            .createdDate(LocalDateTime.now())
            .build()
        );

        super.mockMvc.perform(get("/api/post/{id}", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(PostDocumentation.find());
    }

    @Test
    void 게시글_수정() throws Exception {
        PostUpdateRequest request = PostUpdateRequest.builder()
            .postId(1L)
            .title("수정할 게시글 내용")
            .content("수정할 게시글 내용")
            .images(List.of(uploadTestFile))
            .build();
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
