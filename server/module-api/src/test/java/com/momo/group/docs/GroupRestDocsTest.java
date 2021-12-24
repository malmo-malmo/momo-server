package com.momo.group.docs;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
            .andDo(GroupDocumentation.createGroup());
    }
}
