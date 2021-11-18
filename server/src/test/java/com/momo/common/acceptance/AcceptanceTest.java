package com.momo.common.acceptance;

import static org.springframework.http.HttpHeaders.LOCATION;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.momo.security.TokenProvider;
import com.momo.user.domain.model.User;
import com.momo.user.domain.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseCleanUp databaseCleanUp;

    @Autowired
    private TokenProvider tokenProvider;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @AfterEach
    public void tearDown() {
        databaseCleanUp.afterPropertiesSet();
        databaseCleanUp.cleanUp();
    }

    protected String getAccessToken(User user) {
        Long userId = userRepository.save(user).getId();
        return tokenProvider.createToken(String.valueOf(userId));
    }

    protected Long extractId(ExtractableResponse<Response> response) {
        String[] split = response.header(LOCATION).split("/");
        return Long.parseLong(split[split.length - 1]);
    }

    protected <T> T getObject(ExtractableResponse<Response> res, Class<T> objectType) {
        return res.jsonPath().getObject(".", objectType);
    }

    protected <T> List<T> getObjects(ExtractableResponse<Response> res, Class<T> objectType) {
        return res.jsonPath().getList(".", objectType);
    }
}
