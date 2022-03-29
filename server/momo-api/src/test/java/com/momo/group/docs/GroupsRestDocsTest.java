package com.momo.group.docs;

import static com.momo.GroupFixture.getGroupCardResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.group.GroupsController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.group.service.impl.GroupServiceImpl;
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
    void 모임_목록_조회_검색_V1() throws Exception {
        when(groupService.findPageBySearchConditionV1(any(), any()))
            .thenReturn(List.of(getGroupCardResponse()));

        super.mockMvc.perform(get("/api/groups/search-v1/paging")
                .param("groupName", "모임 이름")
                .param("cities", String.valueOf(City.SEOUL))
                .param("cities", String.valueOf(City.GYEONGGI))
                .param("categories", String.valueOf(Category.HOBBY))
                .param("categories", String.valueOf(Category.LIFE))
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageBySearchConditionV1());
    }

    @Test
    void 모임_목록_조회_검색_V2() throws Exception {
        when(groupService.findPageBySearchConditionV2(any(), any()))
            .thenReturn(List.of(getGroupCardResponse()));

        super.mockMvc.perform(get("/api/groups/search-v2/paging")
                .param("groupName", "모임 이름")
                .param("cities", String.valueOf(City.SEOUL))
                .param("cities", String.valueOf(City.GYEONGGI))
                .param("categories", String.valueOf(Category.HOBBY))
                .param("categories", String.valueOf(Category.LIFE))
                .param("page", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageBySearchConditionV2());
    }

    @Test
    void 모임_목록_조회_내학교더보기() throws Exception {
        when(groupService.findPageByUserUniversity(any(), anyLong(), anyInt()))
            .thenReturn(List.of(getGroupCardResponse()));

        super.mockMvc.perform(get("/api/groups/user-university/paging")
                .param("lastGroupId", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageByUserUniversity());
    }

    @Test
    void 모임_목록_조회_주변더보기() throws Exception {
        when(groupService.findPageByUserDistrict(any(), anyLong(), anyInt()))
            .thenReturn(List.of(getGroupCardResponse()));

        super.mockMvc.perform(get("/api/groups/user-district/paging")
                .param("lastGroupId", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageByUserLocation());
    }

    @Test
    void 모임_목록_조회_추천더보기() throws Exception {
        when(groupService.findPageByUserCategories(any(), anyLong(), anyInt()))
            .thenReturn(List.of(getGroupCardResponse()));

        super.mockMvc.perform(get("/api/groups/user-categories/paging")
                .param("lastGroupId", String.valueOf(1))
                .param("size", String.valueOf(10)))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findPageByUserCategories());
    }
}
