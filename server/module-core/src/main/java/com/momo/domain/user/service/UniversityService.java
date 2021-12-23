package com.momo.domain.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.dto.UniversityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class UniversityService {

    @Value("${app.openApi.career-net.url}")
    private String secretUrl;

    public List<UniversityResponse> findByUniversityName(String universityName) {
        List<UniversityResponse> responses = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String apiUrl = secretUrl + "&searchSchulNm=" + URLEncoder.encode(universityName,
                StandardCharsets.UTF_8);
            URL url = new URL(apiUrl);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            JsonNode jn = mapper.readTree(is);
            JsonNode jn2 = jn.get("dataSearch").get("content");
            Iterator<JsonNode> iter = jn2.elements();
            while (iter.hasNext()) {
                JsonNode jn3 = iter.next();
                responses.add(new UniversityResponse(jn3.get("schoolName").textValue()));
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.CAREER_NET_SERVER_ERROR);
        }
        return responses;
    }
}
