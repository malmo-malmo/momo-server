package com.momo.management.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.management.ManagementController;
import com.momo.common.RestDocsControllerTest;
import com.momo.group.domain.category.Category;
import com.momo.management.dto.MyGroupCardResponse;
import com.momo.management.dto.MyGroupSummaryResponse;
import com.momo.management.dto.MyPostCardResponse;
import com.momo.management.dto.ParticipationGroupCardResponse;
import com.momo.management.dto.ParticipationGroupCountResponse;
import com.momo.management.dto.ParticipationGroupSummaryResponse;
import com.momo.management.service.ManagementService;
import com.momo.post.dto.PostCardResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(ManagementController.class)
@DisplayName("관리 문서화 테스트")
public class ManagementRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private ManagementController managementController;

    @MockBean
    private ManagementService managementService;

    @Test
    void 참여한_모임_수_조회() throws Exception {
        ParticipationGroupCountResponse response = new ParticipationGroupCountResponse(10L);

        when(managementService.findParticipationGroupCountByUser(any())).thenReturn(response);

        super.mockMvc.perform(get("/api/management/participation-group/count"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findParticipationGroupCount());
    }

    @Test
    void 참여한_모임_목록_조회() throws Exception {
        List<ParticipationGroupCardResponse> responses = List.of(
            ParticipationGroupCardResponse.builder()
                .id(1L)
                .name("모임 이름")
                .imageUrl("이미지 URL")
                .startDate(LocalDate.of(2022, 1, 16))
                .isOffline(true)
                .isEnd(false)
                .participantCnt(10L)
                .build()
        );

        when(managementService.findParticipationGroupsByUser(any())).thenReturn(responses);

        super.mockMvc.perform(get("/api/management/participation-groups/details"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findParticipationGroups());
    }

    @Test
    void 그_외_참여한_모임_목록_조회_요약() throws Exception {
        List<ParticipationGroupSummaryResponse> responses = List.of(
            ParticipationGroupSummaryResponse.builder()
                .id(1L)
                .name("모임 이름")
                .category(Category.HOBBY)
                .build()
        );

        when(managementService.findParticipationGroupsSummaryByUser(any())).thenReturn(responses);

        super.mockMvc.perform(get("/api/management/participation-groups/summary"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findParticipationGroupsSummary());
    }

    @Test
    void 내_모임_목록_조회() throws Exception {
        List<MyGroupCardResponse> responses = List.of(
            MyGroupCardResponse.builder()
                .id(1L)
                .name("모임 이름")
                .imageUrl("이미지 URL")
                .achievementRate(0)
                .build()
        );

        when(managementService.findMyGroupsByUser(any())).thenReturn(responses);

        super.mockMvc.perform(get("/api/management/my-groups/details"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findMyGroups());
    }

    @Test
    void 내_모임_목록_조회_요약() throws Exception {
        List<MyGroupSummaryResponse> responses = List.of(
            MyGroupSummaryResponse.builder()
                .id(1L)
                .name("모임 이름")
                .build()
        );

        when(managementService.findMyGroupsSummaryByUser(any())).thenReturn(responses);

        super.mockMvc.perform(get("/api/management/my-groups/summary"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findMyGroupsSummary());
    }

    @Test
    void 내_게시글_목록_조회() throws Exception {
        List<MyPostCardResponse> responses = List.of(
            MyPostCardResponse.builder()
                .groupName("모임 이름")
                .postCardResponse(
                    PostCardResponse.builder()
                        .id(1L)
                        .authorImage("이미지 URL")
                        .authorNickname("닉네임")
                        .title("제목")
                        .contents("내용")
                        .createdDate(LocalDateTime.now())
                        .commentCnt(1L)
                        .build()
                )
                .build()
        );

        when(managementService.findMyPostsByUser(any(), anyLong(), anyInt())).thenReturn(responses);

        super.mockMvc.perform(get("/api/management/my-posts")
                .param("lastPostId", "0")
                .param("size", "10")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findMyPosts());
    }
}
