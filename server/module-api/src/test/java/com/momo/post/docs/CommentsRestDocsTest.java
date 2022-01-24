package com.momo.post.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.RestDocsControllerTest;
import com.momo.api.post.CommentsController;
import com.momo.domain.post.dto.CommentsResponse;
import com.momo.domain.post.entity.Comment;
import com.momo.domain.post.service.CommentService;
import com.momo.domain.user.entity.User;
import java.time.LocalDateTime;
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
    public void 게시물_댓글_목록_조회() throws Exception {
        when(commentService.findPageByPostId(any(), any())).thenReturn(CommentsResponse.of(
            List.of(
                Comment.builder()
                    .id(1L)
                    .user(User.builder().id(1L).imageUrl("http://~~").nickname("테스트맨").build())
                    .contents("테스트 댓글")
                    .createdDate(LocalDateTime.now())
                    .build()
            ),
            1L
        ));

        super.mockMvc.perform(get("/api/comments/paging")
                .param("postId", String.valueOf(1L))
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(CommentDocumentation.findPageByPost());
    }
}
