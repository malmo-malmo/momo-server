package com.momo.common.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.momo.security.TokenProvider;
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
  private DatabaseCleanUp databaseCleanUp;

  @Autowired
  protected TokenProvider tokenProvider;

  protected ObjectMapper objectMapper;

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

  protected String createAccessToken(Long userId) {
    return tokenProvider.createToken(String.valueOf(userId));
  }

  protected <T> T getObject(ExtractableResponse<Response> res, Class<T> objectType) {
    return res.jsonPath().getObject(".", objectType);
  }

  protected <T> List<T> getObjects(ExtractableResponse<Response> res, Class<T> objectType) {
    return res.jsonPath().getList(".", objectType);
  }
}
