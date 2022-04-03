package com.momo.chat.api.docs;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.chat.api.ApplyParticipantController;
import com.momo.chat.api.docs.document.ApplyParticipantDocumentation;
import com.momo.chat.domain.service.ApplyParticipantUseCase;
import com.momo.common.RestDocsControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ApplyParticipantController.class)
@DisplayName("모임 참여 승인 API 테스트")
public class ApplyParticipantControllerTests extends RestDocsControllerTest {

    @MockBean
    private ApplyParticipantUseCase useCase;

    @Test
    @DisplayName("모임 참여 승인")
    void applyParticipant() throws Exception {
        Long chatId = 1L;
        super.mockMvc.perform(post("/api/group/apply-participant/{chatId}", chatId))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ApplyParticipantDocumentation.applyParticipant());
    }
}
