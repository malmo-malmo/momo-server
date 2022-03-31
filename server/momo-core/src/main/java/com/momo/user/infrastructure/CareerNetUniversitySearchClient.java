package com.momo.user.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.momo.common.exception.CustomException;
import com.momo.common.exception.ErrorCode;
import com.momo.user.domain.UniversitySearchClient;
import com.momo.user.domain.dto.UniversityDto;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CareerNetUniversitySearchClient implements UniversitySearchClient {

    private final String apiBaseUrl;

    public CareerNetUniversitySearchClient(
        @Value("${app.open-api.career-net.url}") String apiBaseUrl
    ) {
        this.apiBaseUrl = apiBaseUrl;
    }

    /**
     * TODO: 코드 리팩토링 필요
     */
    @Override
    public List<UniversityDto> search(String universityName) {
        String format = apiBaseUrl + "&searchSchulNm=%s";
        String apiUrl = String.format(format, URLEncoder.encode(universityName, StandardCharsets.UTF_8));

        List<UniversityDto> universityDtos = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(apiUrl);

            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            JsonNode jn = mapper.readTree(is);
            JsonNode jn2 = jn.get("dataSearch").get("content");

            Iterator<JsonNode> iter = jn2.elements();
            while (iter.hasNext()) {
                JsonNode jn3 = iter.next();
                universityDtos.add(new UniversityDto(jn3.get("schoolName").textValue()));
            }
        } catch (Exception e) {
            throw new CustomException(ErrorCode.CAREER_NET_SERVER_ERROR);
        }

        return universityDtos;
    }
}
