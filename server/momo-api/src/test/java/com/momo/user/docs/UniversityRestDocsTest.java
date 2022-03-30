package com.momo.user.docs;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.common.RestDocsControllerTest;
import com.momo.user.UniversityController;
import com.momo.user.dto.response.UniversityResponse;
import com.momo.user.application.UniversityService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(UniversityController.class)
@DisplayName("학교 문서화 테스트")
public class UniversityRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private UniversityController universityController;

    @MockBean
    private UniversityService universityService;

    @Test
    void 대학교_목록_조회() throws Exception {
        when(universityService.findByUniversityName(anyString())).thenReturn(List.of(
            new UniversityResponse("무슨대")
        ));

        super.mockMvc.perform(get("/api/universities")
                .param("universityName", "무슨대")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UniversityDocumentation.findByUniversityName());
    }
}
