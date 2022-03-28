package com.momo.chat.api.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.chat.api.FindChatMessagesController;
import com.momo.chat.api.docs.document.FindChatMessagesDocumentation;
import com.momo.chat.domain.entity.ChatMessageType;
import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.chat.domain.service.FindChatMessagesUseCase;
import com.momo.common.RestDocsControllerTest;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(FindChatMessagesController.class)
@DisplayName("채팅방 메시지 목록 조회 API 테스트")
public class FindChatMessagesApiSpecTests extends RestDocsControllerTest {

    @MockBean
    private FindChatMessagesUseCase useCase;

    @Test
    @DisplayName("채팅방 메시지 목록 조회")
    void findChatMessages() throws Exception {
        given(useCase.findChatMessages(any(), any())).willReturn(List.of(
            SendPublishMessageResponse.builder()
                .message("안녕하세요")
                .creDatetime(LocalDateTime.now())
                .username("도라에몽")
                .profileImageUrl("이미지 주소")
                .messageType(ChatMessageType.NORMAL)
                .isSystem(false)
                .build()
        ));

        Long chatId = 1L;
        super.mockMvc.perform(get("/api/chat/{chatId}/messages", chatId))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(FindChatMessagesDocumentation.findChatMessages());
    }
}
