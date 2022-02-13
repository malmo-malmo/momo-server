package com.momo.schedule.docs;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.common.RestDocsControllerTest;
import com.momo.api.schedule.AttendanceController;
import com.momo.domain.schedule.dto.AttendanceCreateRequest;
import com.momo.domain.schedule.dto.AttendanceCreateRequests;
import com.momo.domain.schedule.dto.AttendanceUpdateRequest;
import com.momo.domain.schedule.dto.AttendanceUpdateRequests;
import com.momo.domain.schedule.service.impl.AttendanceServiceImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(AttendanceController.class)
@DisplayName("출석체크 문서화 테스트")
public class AttendanceRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private AttendanceController attendanceController;

    @MockBean
    private AttendanceServiceImpl attendanceService;

    @Test
    void 출석체크() throws Exception {
        AttendanceCreateRequests requests = AttendanceCreateRequests.builder()
            .scheduleId(1L)
            .attendanceCreateRequests(List.of(AttendanceCreateRequest.builder().participantId(1L).isAttend(true).build()))
            .build();
        String content = super.objectMapper.writeValueAsString(requests);
        super.mockMvc.perform(post("/api/attendance")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(AttendanceDocumentation.create());
    }

    @Test
    void 출석체크_수정() throws Exception {
        AttendanceUpdateRequests requests = AttendanceUpdateRequests.builder()
            .scheduleId(1L)
            .attendanceUpdateRequests(
                List.of(AttendanceUpdateRequest.builder().attendanceId(1L).isAttend(true).build()))
            .build();
        String content = super.objectMapper.writeValueAsString(requests);
        super.mockMvc.perform(put("/api/attendance")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(AttendanceDocumentation.update());
    }
}
