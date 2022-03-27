package com.momo.chat.api.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.chat.api.FindChatsApiSpec;
import com.momo.chat.api.docs.document.FindChatsDocumentation;
import com.momo.chat.domain.response.ChatResponse;
import com.momo.chat.domain.service.FindChatsUseCase;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.common.util.TimeFormatUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(FindChatsApiSpec.class)
@DisplayName("채팅방 목록 조회 API 테스트")
public class FindChatsApiSpecTests extends RestDocsControllerTest {

    @MockBean
    private FindChatsUseCase useCase;

    @Test
    @DisplayName("채팅방 목록 조회")
    void findChats() throws Exception {
        //...given
        Long groupId = 1L;
        LocalDateTime now = LocalDateTime.now();
        given(useCase.findChats(any())).willReturn(List.of(
            ChatResponse.builder()
                .chatId(1L)
                .profileImageUrl("유저 프로필 이미지 주소")
                .username("호돌이")
                .lastMessage("Hello~")
                .createDate(now)
                .createDateMessage(TimeFormatUtil.generateDateInfo(now))
                .build()
        ));

        //...when and then
        super.mockMvc.perform(get("/api/chat/list", groupId))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(FindChatsDocumentation.findChats());
    }
}
