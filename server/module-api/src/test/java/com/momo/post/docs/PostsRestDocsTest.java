package com.momo.post.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.RestDocsControllerTest;
import com.momo.api.post.PostController;
import com.momo.api.post.PostsController;
import com.momo.domain.post.dto.PostCardResponse;
import com.momo.domain.post.service.PostService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(PostsController.class)
@DisplayName("게시글 문서화 테스트")
public class PostsRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private PostController postsController;

    @MockBean
    private PostService postService;

    @Test
    public void 게시글_목록_조회() throws Exception {
        when(postService.findPageByGroupIdAndType(any(), any())).thenReturn(List.of(
            PostCardResponse.builder()
                .id(1L)
                .authorImage("이미지 URL")
                .authorNickname("테스트맨")
                .title("테스트 게시글")
                .contents("테스트 내용")
                .createdDate(LocalDateTime.now())
                .commentCnt(1L)
                .build()
        ));
        super.mockMvc.perform(get("/api/posts/paging", 1L)
                .param("groupId", String.valueOf(1L))
                .param("type", "테스트 타입")
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(PostDocumentation.findPageByCardsRequest());

    }
}
