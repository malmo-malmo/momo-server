package com.momo;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.momo.domain.auth.service.OAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@ActiveProfiles("test")
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
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
                .withScheme(this.SCHEME)
                .withHost(this.HOST)
                .withPort(this.PORT)
                .and()
                .operationPreprocessors()
                .withRequestDefaults(prettyPrint())
                .withResponseDefaults(prettyPrint())
            )
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }
}
