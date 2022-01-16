package com.momo.post.docs;

import static com.momo.CommonFileUploadSupport.uploadMockSupport;
import static com.momo.CommonFileUploadSupport.uploadTestFile;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.RestDocsControllerTest;
import com.momo.api.post.PostController;
import com.momo.domain.post.dto.PostCreateRequest;
import com.momo.domain.post.dto.PostResponse;
import com.momo.domain.post.service.PostService;
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
    private PostService postService;

    @Test
    public void 게시글_작성() throws Exception {
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
    public void 게시글_상세_조회() throws Exception {
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
}