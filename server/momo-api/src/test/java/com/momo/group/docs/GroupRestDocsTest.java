package com.momo.group.docs;


import static com.momo.GroupFixture.getGroupCreateRequest;
import static com.momo.GroupFixture.getGroupCreateResponse;
import static com.momo.GroupFixture.getGroupResponse;
import static com.momo.common.CommonFileUploadSupport.uploadMockSupport;
import static com.momo.district.entity.City.SEOUL;
import static com.momo.group.domain.category.Category.LIFE;
import static com.momo.group.domain.category.Category.STOCK;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.common.RestDocsControllerTest;
import com.momo.group.GroupController;
import com.momo.group.application.GroupService;
import com.momo.group.application.dto.request.GroupCreateRequest;
import com.momo.group.application.dto.request.GroupUpdateRequest;
import com.momo.group.application.dto.response.GroupImageUpdateResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

@WebMvcTest(GroupController.class)
@DisplayName("모임 문서화 테스트")
public class GroupRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    public GroupController groupController;

    @MockBean
    public GroupService groupService;

    @Test
    @DisplayName("모임을 생성한다")
    void createGroup_LoginUser_Success() throws Exception {
        GroupCreateRequest request = getGroupCreateRequest(LIFE, "대학교");
        when(groupService.createGroup(any(), any())).thenReturn(getGroupCreateResponse());

        super.mockMvc.perform(uploadMockSupport(fileUpload("/api/group"), request)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(GroupDocumentation.createGroup());
    }

    @Test
    @DisplayName("모임을 상세 조회한다")
    void findGroup_Manager_Success() throws Exception {
        when(groupService.findGroup(any(), any())).thenReturn(getGroupResponse());

        super.mockMvc.perform(get("/api/group/{groupId}", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findGroup());
    }

    @Test
    @DisplayName("모임을 카테고리 목록 조회")
    void findGroupCategories_Success() throws Exception {
        super.mockMvc.perform(get("/api/group/categories"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.findGroupCategories());
    }

    @Test
    @DisplayName("모임 관리자를 변경한다")
    void updateManager_Manager_Success() throws Exception {
        super.mockMvc.perform(patch("/api/group/{groupId}/manager/{userId}", 1L, 2L))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.updateManagerByUserId());
    }

    @Test
    @DisplayName("모임을 종료한다")
    void end_Manager_Success() throws Exception {
        super.mockMvc.perform(patch("/api/group/{groupId}/end", 1L))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.endGroup());
    }

    @Test
    @DisplayName("모임 정보를 수정한다")
    void updateGroupInformation_Manager_Success() throws Exception {
        GroupUpdateRequest request = GroupUpdateRequest.builder()
            .id(1L)
            .name("운동 모임")
            .category(STOCK)
            .university("대학교")
            .city(SEOUL)
            .district("강동구")
            .recruitmentCnt(10)
            .introduction("모임 설명")
            .isOffline(true)
            .build();

        groupService.updateGroupInformation(any(), any());

        super.mockMvc.perform(put("/api/group/update-information")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.updateGroupInformation());
    }

    @Test
    @DisplayName("모임 대표 이미지를 수정한다")
    void updateGroupImage_Success() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("imageFile", "imageFile".getBytes());
        GroupImageUpdateResponse response = new GroupImageUpdateResponse("imageUrl");

        when(groupService.updateGroupImage(any(), any(), any())).thenReturn(response);

        super.mockMvc.perform(RestDocumentationRequestBuilders.fileUpload("/api/group/{groupId}/update-image", 1L)
                .file(imageFile)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(GroupDocumentation.updateImage());
    }

    @Test
    @DisplayName("모임 대표 이미지를 삭제한다")
    void deleteGroupImage_Success() throws Exception {
        groupService.deleteGroupImage(any(), any());

        super.mockMvc.perform(delete("/api/group/{groupId}/delete-image", 1L))
            .andDo(print())
            .andExpect(status().isNoContent())
            .andDo(GroupDocumentation.deleteImage());
    }
}
