package com.momo.user.docs;

import static com.momo.UserFixture.getUserWithId;
import static com.momo.common.CommonFileUploadSupport.generateUploadMockPutBuilder;
import static com.momo.common.CommonFileUploadSupport.uploadMockSupport;
import static com.momo.common.CommonFixtures.UPLOAD_TEST_FILE;
import static com.momo.domain.district.entity.City.SEOUL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.api.user.UserController;
import com.momo.common.RestDocsControllerTest;
import com.momo.domain.common.dto.EnumResponse;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.dto.UserUpdateResponse;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(UserController.class)
@DisplayName("사용자 문서화 테스트")
public class UserRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void 내_정보_조회() throws Exception {
        User user = getUserWithId();

        when(oAuthService.findLoginUserByAccessToken(any())).thenReturn(user);

        super.mockMvc.perform(get("/api/user"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.findMyInformation());
    }

    @Test
    void 닉네임_중복_확인() throws Exception {
        super.mockMvc.perform(get("/api/user/duplicate-nickname")
                .param("nickname", "테스트 이름")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.validateDuplicateNickname());
    }

    @Test
    void 내_정보_수정_이미지_미포함() throws Exception {
        UserUpdateResponse response = UserUpdateResponse.builder()
            .nickname("테스트 이름")
            .university("한국대")
            .city(EnumResponse.ofCity(SEOUL))
            .district("마포구")
            .imageUrl("이미지 URL X")
            .build();

        when(userService.update(any(), any())).thenReturn(response);

        super.mockMvc.perform(put("/api/user")
                .param("nickname", "테스트 이름")
                .param("university", "한국대")
                .param("city", SEOUL.getCode())
                .param("district", "마포구")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.updateMyInformation());
    }

    @Test
    void 내_정보_수정_이미지_포함() throws Exception {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .nickname("테스트 이름")
            .university("한국대")
            .city(SEOUL)
            .district("마포구")
            .image(UPLOAD_TEST_FILE)
            .build();
        UserUpdateResponse response = UserUpdateResponse.builder()
            .nickname("테스트 이름")
            .university("한국대")
            .city(EnumResponse.ofCity(SEOUL))
            .district("마포구")
            .imageUrl("이미지 URL O")
            .build();

        when(userService.update(any(), any())).thenReturn(response);

        super.mockMvc.perform(uploadMockSupport(generateUploadMockPutBuilder(fileUpload("/api/user")), request))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.updateMyInformationWithImage());
    }
}
