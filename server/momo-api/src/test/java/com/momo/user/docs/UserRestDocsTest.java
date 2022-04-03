package com.momo.user.docs;

import static com.momo.UserFixture.getUserWithId;
import static com.momo.common.docs.MockMvcHttpServletRequestSupport.mockMultipartPutBuilder;
import static com.momo.district.entity.City.SEOUL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.common.RestDocsControllerTest;
import com.momo.common.dto.EnumResponse;
import com.momo.user.UserController;
import com.momo.user.application.UserService;
import com.momo.user.application.dto.request.UserUpdateRequest;
import com.momo.user.application.dto.response.UserImageUpdateResponse;
import com.momo.user.application.dto.response.UserResponse;
import com.momo.user.application.dto.response.UserUpdateResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@WebMvcTest(UserController.class)
@DisplayName("사용자 문서화 테스트")
public class UserRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("내 정보를 조회한다")
    void findMyInformation_LoginUser_Success() throws Exception {
        UserResponse response = UserResponse.builder()
            .id(1L)
            .nickname("닉네임")
            .imageUrl("이미지 URL")
            .city(EnumResponse.ofCity(SEOUL))
            .district("강동구")
            .university("서울대학교")
            .categories(EnumResponse.listOfFavoriteCategories(getUserWithId().getFavoriteCategories()))
            .build();

        when(userService.findMyInformation(any())).thenReturn(response);

        super.mockMvc.perform(get("/api/user"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.findMyInformation());
    }

    @Test
    @DisplayName("닉네임 중복 여부를 확인한다")
    void validateDuplicateNickname_Success() throws Exception {
        super.mockMvc.perform(get("/api/user/duplicate-nickname")
                .param("nickname", "테스트 이름")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.validateDuplicateNickname());
    }

    @Test
    @DisplayName("내 정보를 수정한다")
    void updateMyInformation_LoginUser_Success() throws Exception {
        UserUpdateRequest request = UserUpdateRequest.builder()
            .nickname("테스트 이름")
            .university("한국대")
            .city(SEOUL)
            .district("마포구")
            .build();

        UserUpdateResponse response = UserUpdateResponse.builder()
            .nickname("테스트 이름")
            .university("한국대")
            .city(EnumResponse.ofCity(SEOUL))
            .district("마포구")
            .build();

        when(userService.updateMyInformation(any(), any())).thenReturn(response);

        super.mockMvc.perform(put("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.updateMyInformation());
    }

    @Test
    @DisplayName("내 프로필 이미지를 수정한다 - 이미지 포함")
    void updateImage_ExistsImage_Success() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("imageFile", "imageFile".getBytes());
        UserImageUpdateResponse response = new UserImageUpdateResponse("imageUrl");

        when(userService.updateImage(any(), any())).thenReturn(response);

        super.mockMvc.perform(mockMultipartPutBuilder("/api/user/update-image")
                .file(imageFile)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.updateImageWithImage());
    }

    @Test
    @DisplayName("내 프로필 이미지를 수정한다 - 이미지 미포함")
    void updateImage_NotExistsImage_Success() throws Exception {
        UserImageUpdateResponse response = new UserImageUpdateResponse("null");

        when(userService.updateImage(any(), any())).thenReturn(response);

        super.mockMvc.perform(put("/api/user/update-image"))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.updateImage());
    }
}
