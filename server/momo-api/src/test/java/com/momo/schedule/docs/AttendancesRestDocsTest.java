package com.momo.schedule.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.schedule.AttendanceController;
import com.momo.schedule.AttendancesController;
import com.momo.common.RestDocsControllerTest;
import com.momo.schedule.dto.AttendanceResponse;
import com.momo.schedule.service.impl.AttendanceServiceImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

@WebMvcTest(AttendancesController.class)
@DisplayName("출석체크 문서화 테스트")
public class AttendancesRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private AttendanceController attendanceController;

    @MockBean
    private AttendanceServiceImpl attendanceService;

    @Test
    void 출석체크_목록_조회() throws Exception {
        given(attendanceService.findScheduleAttendances(any(), any())).willReturn(List.of(
            AttendanceResponse.builder()
                .attendanceId(1L)
                .nickname("테스트 이름")
                .imageUrl("이미지 URL")
                .attendanceRate(100)
                .isAttend(false)
                .build()
        ));
        super.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/attendances/schedule/{scheduleId}", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(AttendanceDocumentation.findGroupAttendances());
    }
}
