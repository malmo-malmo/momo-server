package com.momo.group.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.RestDocsControllerTest;
import com.momo.api.group.GroupsController;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.dto.GroupCardResponse;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.service.impl.GroupServiceImpl;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(GroupsController.class)
@DisplayName("모임 목록 문서화 테스트")
public class GroupsRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    public GroupsController groupsController;

    @MockBean
    public GroupServiceImpl groupService;

    @Test
    public void 모임_목록_조회_검색() throws Exception {
        when(groupService.findPageBySearchCondition(any(), any()))
            .thenReturn(List.of(
                GroupCardResponse.builder()
                    .id(1L)
                    .name("A 모임")
                    .imageUrl("http://~~")
                    .startDate(LocalDate.now())
                    .isOffline(false)
                    .participantCnt(3L)
                    .build()
            ));
        super.mockMvc.perform(get("/api/groups/search/paging")
                .param("cities", String.valueOf(City.SEOUL))
                .param("cities", String.valueOf(City.GYEONGGI))
                .param("categories", String.valueOf(Category.HOBBY))
                .param("categories", String.valueOf(Category.LIFE))
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageBySearchCondition());
    }

    @Test
    public void 모임_목록_조회_내학교더보기() throws Exception {
        when(groupService.findPageByUserUniversity(any(), anyInt(), anyInt()))
            .thenReturn(List.of(
                GroupCardResponse.builder()
                    .id(1L)
                    .name("A 모임")
                    .imageUrl("http://~~")
                    .startDate(LocalDate.now())
                    .isOffline(false)
                    .participantCnt(3L)
                    .build()
            ));
        super.mockMvc.perform(get("/api/groups/user-university/paging")
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageByUserUniversity());
    }

    @Test
    public void 모임_목록_조회_주변더보기() throws Exception {
        when(groupService.findPageByUserDistrict(any(), anyInt(), anyInt()))
            .thenReturn(List.of(
                GroupCardResponse.builder()
                    .id(1L)
                    .name("A 모임")
                    .imageUrl("http://~~")
                    .startDate(LocalDate.now())
                    .isOffline(false)
                    .participantCnt(3L)
                    .build()
            ));
        super.mockMvc.perform(get("/api/groups/user-district/paging")
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageByUserLocation());
    }

    @Test
    public void 모임_목록_조회_추천더보기() throws Exception {
        when(groupService.findPageByUserCategories(any(), anyInt(), anyInt()))
            .thenReturn(List.of(
                GroupCardResponse.builder()
                    .id(1L)
                    .name("A 모임")
                    .imageUrl("http://~~")
                    .startDate(LocalDate.now())
                    .isOffline(false)
                    .participantCnt(3L)
                    .build()
            ));
        super.mockMvc.perform(get("/api/groups/user-categories/paging")
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageByUserCategories());
    }
}
