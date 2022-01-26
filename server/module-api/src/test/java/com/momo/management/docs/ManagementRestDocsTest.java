package com.momo.management.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.RestDocsControllerTest;
import com.momo.api.management.ManagementController;
import com.momo.domain.management.dto.MyPostCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCardResponse;
import com.momo.domain.management.dto.ParticipatingGroupCountResponse;
import com.momo.domain.management.service.ManagementService;
import com.momo.domain.post.dto.PostCardResponse;
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
        ParticipatingGroupCountResponse response = new ParticipatingGroupCountResponse(10L);
        when(managementService.findParticipatingGroupCountByUser(any())).thenReturn(response);
        super.mockMvc.perform(get("/api/management/group/participation/count"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findParticipatingGroupCount());
    }

    @Test
    void 참여한_모임_목록_조회() throws Exception {
        List<ParticipatingGroupCardResponse> responses = List.of(
            ParticipatingGroupCardResponse.builder()
                .id(1L)
                .name("모임 이름")
                .imageUrl("이미지 URL")
                .startDate(LocalDate.of(2022, 1, 16))
                .isOffline(true)
                .isEnd(false)
                .participantCnt(10L)
                .build()
        );
        when(managementService.findParticipatingGroupsByUser(any())).thenReturn(responses);
        super.mockMvc.perform(get("/api/management/groups/participation"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findParticipatingGroups());
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
        when(managementService.findMyPostsByUser(any(), anyInt(), anyInt())).thenReturn(responses);
        super.mockMvc.perform(get("/api/management/posts")
                .param("page", String.valueOf(0))
                .param("size", String.valueOf(10))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(ManagementDocumentation.findMyPosts());
    }
}
