package com.momo.post.docs;

import static com.momo.PostFixture.getPostCardResponse;
import static com.momo.post.entity.PostType.NORMAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.post.PostsController;
import com.momo.common.RestDocsControllerTest;
import com.momo.post.service.PostService;
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
    private PostsController postsController;

    @MockBean
    private PostService postService;

    @Test
    void 게시글_목록_조회() throws Exception {
        when(postService.findPageByGroupId(any(), any())).thenReturn(List.of(getPostCardResponse()));

        super.mockMvc.perform(get("/api/posts/paging", 1L)
                .param("groupId", String.valueOf(1L))
                .param("postType", NORMAL.getCode())
                .param("lastPostId", String.valueOf(1))
                .param("size", String.valueOf(10))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(PostDocumentation.findPageByCardsRequest());
    }
}
