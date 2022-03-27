package com.momo.chat.api.docs;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.chat.api.CreateChatApiSpec;
import com.momo.chat.api.docs.document.ApplyParticipantDocumentation;
import com.momo.chat.api.docs.document.CreateChatDocumentation;
import com.momo.chat.domain.service.CreateChatUseCase;
import com.momo.common.RestDocsControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CreateChatApiSpec.class)
@DisplayName("채팅방 생성 API 테스트")
public class CreateChatApiSpecTests extends RestDocsControllerTest {
    @MockBean
    private CreateChatUseCase useCase;

    @Test
    @DisplayName("채팅방 생성")
    void createChats() throws Exception {
        Long groupId = 1L;
        super.mockMvc.perform(post("/api/chat/group/{groupId}", groupId))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(CreateChatDocumentation.createChat());
    }
}
