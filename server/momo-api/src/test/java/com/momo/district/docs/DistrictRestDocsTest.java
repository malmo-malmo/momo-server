package com.momo.district.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.district.DistrictController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.district.entity.District;
import com.momo.domain.district.repository.DistrictRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(DistrictController.class)
@DisplayName("구역 문서화 테스트")
public class DistrictRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private DistrictController districtController;

    @MockBean
    private DistrictRepository districtRepository;

    @Test
    void 시_도_목록_조회() throws Exception {
        super.mockMvc.perform(get("/api/district/cities"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(DistrictDocumentation.findCities());
    }

    @Test
    void 구_군_목록_조회() throws Exception {
        when(districtRepository.findAllByCity(any())).thenReturn(List.of(
            District.builder().districtName("경상도").build()
        ));
        super.mockMvc.perform(get("/api/district/districts")
                .param("cityCode", "SEOUL"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(DistrictDocumentation.findDistrictsByCity());

    }
}
