package com.momo.user.docs;

import static com.momo.CommonFileUploadSupport.uploadMockSupport;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.fileUpload;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.RestDocsControllerTest;
import com.momo.api.user.UserController;
import com.momo.domain.district.entity.City;
import com.momo.domain.group.entity.Category;
import com.momo.domain.user.dto.UserUpdateRequest;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.service.UserService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

@WebMvcTest(UserController.class)
@DisplayName("사용자 문서화 테스트")
public class UserRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void 내_정보_조회() throws Exception {
        User user = User.builder()
            .id(1L)
            .nickname("테스트맨")
            .imageUrl("이미지 URL")
            .city(City.SEOUL)
            .district("마포구")
            .university("한국대")
            .build();
        user.updateFavoriteCategories(List.of(Category.LIFE));

        when(oAuthService.findLoginUserByAccessToken(any()))
            .thenReturn(user);

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
        UserUpdateRequest request = UserUpdateRequest.builder()
            .nickname("테스트 이름")
            .university("한국대")
            .city(City.SEOUL)
            .district("마포구")
            .build();
        super.mockMvc.perform(post("/api/user/update")
                .param("nickname", request.getNickname())
                .param("university", request.getUniversity())
                .param("city", request.getCity().getCode())
                .param("district", request.getDistrict())
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
            .city(City.SEOUL)
            .district("마포구")
            .image(new MockMultipartFile("image", "image".getBytes()))
            .build();
        super.mockMvc.perform(uploadMockSupport(fileUpload("/api/user/update"), request))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(UserDocumentation.updateMyInformationWithImage());
    }
}
