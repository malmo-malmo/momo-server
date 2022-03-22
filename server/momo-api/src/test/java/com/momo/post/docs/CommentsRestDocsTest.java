package com.momo.post.docs;

import static com.momo.CommentFixture.getCommentResponse;
import static com.momo.CommentFixture.getCommentsResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.api.post.CommentsController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.post.service.CommentService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CommentsController.class)
@DisplayName("댓글 문서화 테스트")
public class CommentsRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private CommentsController commentsController;

    @MockBean
    private CommentService commentService;

    @Test
    void 게시물_댓글_목록_조회() throws Exception {
        when(commentService.findPageByPostId(any(), any()))
            .thenReturn(getCommentsResponse(List.of(getCommentResponse())));

        super.mockMvc.perform(get("/api/comments/paging")
                .param("postId", "1")
                .param("lastCommentId", "1")
                .param("size", "10")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(CommentDocumentation.findPageByPost());
    }
}
