package com.momo.group.docs;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.RestDocsControllerTest;
import com.momo.api.group.GroupController;
import com.momo.domain.group.dto.GroupCreateRequest;
import com.momo.domain.group.service.GroupService;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@WebMvcTest(GroupController.class)
@DisplayName("모임 문서화 테스트")
public class GroupRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    public GroupController groupController;
    @MockBean
    public GroupService groupService;

    @Test
    public void 모임_생성_테스트() throws Exception {
        when(groupService.create(any(), any())).thenReturn(1l);
        GroupCreateRequest request = GroupCreateRequest.builder()
            .name("A 모임")
            .category("HOBBY")
            .isUniversity(true)
            .city("서울")
            .district("마포구")
            .startDate(LocalDate.now())
            .recruitmentCnt(4)
            .introduction("테스트 모임입니다.")
            .imageUrl("http://~~")
            .isOffline(false)
            .build();
        String content = super.objectMapper.writeValueAsString(request);
        super.mockMvc.perform(post("/api/group")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("group/create",
                requestFields(
                    fieldWithPath("name").type(JsonFieldType.STRING).description("모임 이름"),
                    fieldWithPath("category").type(JsonFieldType.STRING).description("모임 카테고리"),
                    fieldWithPath("isUniversity").type(JsonFieldType.BOOLEAN).description("모임 학교 여부"),
                    fieldWithPath("city").type(JsonFieldType.STRING).description("모임 지역"),
                    fieldWithPath("district").type(JsonFieldType.STRING).description("모임 구역"),
                    fieldWithPath("startDate").type(JsonFieldType.STRING).description("모임 시작일자"),
                    fieldWithPath("recruitmentCnt").type(JsonFieldType.NUMBER).description("모임 인원수"),
                    fieldWithPath("introduction").type(JsonFieldType.STRING).description("모임 설명"),
                    fieldWithPath("imageUrl").type(JsonFieldType.STRING).description("모임 이미지 주소"),
                    fieldWithPath("isOffline").type(JsonFieldType.BOOLEAN).description("모임 온/오프라인 여부")
                )));
    }
}
