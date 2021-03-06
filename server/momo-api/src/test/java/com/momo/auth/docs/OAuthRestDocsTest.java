package com.momo.auth.docs;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.momo.common.RestDocsControllerTest;
import com.momo.auth.OAuthController;
import com.momo.auth.dto.OAuthLoginRequest;
import com.momo.auth.dto.OAuthLoginResponse;
import com.momo.auth.dto.RefreshLoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

@WebMvcTest(OAuthController.class)
@DisplayName("인가 문서화 테스트")
public class OAuthRestDocsTest extends RestDocsControllerTest {

    @InjectMocks
    private OAuthController oAuthController;

    @Test
    void 로그인() throws Exception {
        when(oAuthService.oauthLogin(any())).thenReturn(
            new OAuthLoginResponse("액세스 토큰", "리프레쉬 토큰")
        );
        OAuthLoginRequest request = new OAuthLoginRequest("google", "인가코드", "deviceCode");
        String content = super.objectMapper.writeValueAsString(request);
        super.mockMvc.perform(post("/api/oauth/login")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(OAuthDocumentation.oauthLogin());
    }

    @Test
    void 재발급_로그인() throws Exception {
        when(oAuthService.refreshLogin(any())).thenReturn(
            new OAuthLoginResponse("액세스 토큰", "리프레쉬 토큰")
        );
        RefreshLoginRequest request = new RefreshLoginRequest("리프레쉬 토큰", "deviceCode");
        String content = super.objectMapper.writeValueAsString(request);
        super.mockMvc.perform(post("/api/oauth/login/refresh")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(OAuthDocumentation.refreshLogin());
    }
}
