package com.momo.post.docs;

import static com.momo.CommentFixture.getCommentCreateRequest;
import static com.momo.CommentFixture.getCommentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.api.post.CommentController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.post.dto.CommentResponse;
import com.momo.domain.post.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(CommentController.class)
@DisplayName("댓글 문서화 테스트")
public class CommentRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private CommentController commentController;

    @MockBean
    private CommentServiceImpl commentService;

    @Test
    void 게시물_댓글_등록() throws Exception {
        CommentResponse response = getCommentResponse();

        when(commentService.createComment(any(), any())).thenReturn(response);

        String content = super.objectMapper.writeValueAsString(getCommentCreateRequest(1L));
        super.mockMvc.perform(post("/api/comment")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(CommentDocumentation.create());
    }
}
