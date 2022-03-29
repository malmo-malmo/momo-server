package com.momo.group.docs;


import static com.momo.ParticipantFixture.getParticipantResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.group.ParticipantController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.group.service.impl.ParticipantServiceImpl;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(ParticipantController.class)
@DisplayName("모임 문서화 테스트")
public class ParticipantRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private ParticipantController participantController;

    @MockBean
    private ParticipantServiceImpl participantService;

    @Test
    void 모임_참여_신청() throws Exception {
        String content = super.objectMapper.writeValueAsString(Map.of("groupId", 1L));

        super.mockMvc.perform(post("/api/group/apply-participant")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(ParticipantDocumenttation.applyParticipantByGroup());
    }

    @Test
    void 모임_참여자_목록_조회() throws Exception {
        when(participantService.findByGroupId(any(), anyLong()))
            .thenReturn(List.of(getParticipantResponse()));

        super.mockMvc.perform(get("/api/group/participants")
                .param("groupId", String.valueOf(1L)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ParticipantDocumenttation.findByGroup());
    }

    @Test
    void 모임_탈퇴() throws Exception {
        super.mockMvc.perform(delete("/api/group/{groupId}/participant", 1L))
            .andDo(print())
            .andExpect(status().isNoContent())
            .andDo(ParticipantDocumenttation.delete());
    }
}
