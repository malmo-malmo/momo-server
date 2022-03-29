package com.momo.schedule.docs;

import static com.momo.ScheduleFixture.getUpcomingScheduleResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.schedule.ScheduleController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.schedule.dto.GroupScheduleResponse;
import com.momo.domain.schedule.dto.GroupScheduleResponses;
import com.momo.domain.schedule.dto.ScheduleCreateRequest;
import com.momo.domain.schedule.dto.UserScheduleResponse;
import com.momo.domain.schedule.service.impl.ScheduleServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(ScheduleController.class)
@DisplayName("일정 문서화 테스트")
public class ScheduleRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private ScheduleController scheduleController;

    @MockBean
    private ScheduleServiceImpl scheduleService;

    @Test
    void 일정_등록() throws Exception {
        ScheduleCreateRequest request = ScheduleCreateRequest.builder()
            .groupId(1L)
            .title("일정 이름")
            .contents("일정 내용")
            .isOffline(true)
            .startDateTime(LocalDateTime.now())
            .build();
        String content = super.objectMapper.writeValueAsString(request);

        given(scheduleService.createSchedule(any(), any())).willReturn(GroupScheduleResponse.builder()
            .scheduleId(1L)
            .authorImage("작성자 이미지")
            .authorNickname("작성자 이름")
            .title("오늘의 일정")
            .isOffline(false)
            .startDateTime(LocalDateTime.now())
            .contents("오늘의 일정 내용")
            .attendanceCheck(false)
            .isAttend(false)
            .build());

        super.mockMvc.perform(post("/api/schedule")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(ScheduleDocumentation.create());
    }

    @Test
    void 모임_일정_조회() throws Exception {
        when(scheduleService.findPageByUserAndGroupId(any(), any())).thenReturn(GroupScheduleResponses.of(
            List.of(
                GroupScheduleResponse.builder()
                    .scheduleId(1L)
                    .authorImage("http://~~")
                    .authorNickname("일정 작성자 닉네임")
                    .title("일정 이름")
                    .contents("테스트 일정")
                    .isOffline(false)
                    .startDateTime(LocalDateTime.now())
                    .attendanceCheck(false)
                    .isAttend(false)
                    .build()
            ),
            1L
        ));
        super.mockMvc.perform(get("/api/schedule/group-schedules")
                .param("groupId", "1")
                .param("lastScheduleStartDateTime", "2021-10-18 10:10")
                .param("size", "10")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ScheduleDocumentation.findPageByGroup());
    }

    @Test
    void 캘린더_일정_조회() throws Exception {
        when(scheduleService.findPageByUserAndSearchDate(any(), any())).thenReturn(List.of(
            UserScheduleResponse.builder()
                .groupId(1L)
                .groupCategory(EnumResponse.ofCategory(Category.EMPLOYMENT))
                .title("오늘의 일정")
                .startDateTime(LocalDateTime.now())
                .build()
        ));
        super.mockMvc.perform(get("/api/schedule/user-schedules")
                .param("searchStartDate", "2021-12-01")
                .param("searchEndDate", "2021-12-02")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ScheduleDocumentation.findPageByUser());
    }

    @Test
    void 다가오는_일정_조회() throws Exception {
        when(scheduleService.findUpcomingScheduleByGroupId(any(), any())).thenReturn(getUpcomingScheduleResponse());

        super.mockMvc.perform(get("/api/schedule/upcoming")
                .param("groupId", String.valueOf(1L))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ScheduleDocumentation.findUpcomingSchedule());
    }
}
