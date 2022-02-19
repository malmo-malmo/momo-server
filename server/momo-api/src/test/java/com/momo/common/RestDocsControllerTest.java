package com.momo.common;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momo.TestProfile;
import com.momo.config.JasyptConfig;
import com.momo.domain.auth.provider.TokenProvider;
import com.momo.domain.auth.service.OAuthService;
import com.momo.config.InterceptorConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ActiveProfiles(TestProfile.TEST)
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@Import({InterceptorConfig.class, TokenProvider.class, JasyptConfig.class})
public class RestDocsControllerTest {

    private static final String SCHEME = "http";
    private static final String HOST = "gunimon.iptime.org";
    private static final int PORT = 8100;

    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    public OAuthService oAuthService;

    @BeforeEach
    void setUp(
        WebApplicationContext webApplicationContext,
        RestDocumentationContextProvider restDocumentationContextProvider
    ) {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentationContextProvider)
                .uris()
                .withScheme(SCHEME)
                .withHost(HOST)
                .withPort(PORT)
                .and()
                .operationPreprocessors()
                .withRequestDefaults(prettyPrint())
                .withResponseDefaults(prettyPrint())
            )
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }
}
