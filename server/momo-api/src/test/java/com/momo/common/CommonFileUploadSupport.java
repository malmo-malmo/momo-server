package com.momo.common;

import io.restassured.specification.RequestSpecification;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

public class CommonFileUploadSupport {

    public static RequestSpecification uploadAssuredSupport(RequestSpecification spec, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(obj);
                if (!Objects.isNull(fieldValue)) {
                    if (fieldValue instanceof MockMultipartFile) {
                        MockMultipartFile file = (MockMultipartFile) fieldValue;
                        spec = spec.multiPart(fieldName, fieldName, file.getBytes());
                    } else if (fieldValue instanceof List) {
                        if (!((List<?>) fieldValue).isEmpty()) {
                            Object fieldObj = Optional.ofNullable(((List<?>) fieldValue).get(0))
                                .orElseThrow();
                            if (fieldObj instanceof MockMultipartFile) {
                                for (MockMultipartFile file : ((List<MockMultipartFile>) fieldValue)) {
                                    spec = spec.multiPart(fieldName, fieldName, file.getBytes());
                                }
                            }
                        }
                    } else {
                        spec = spec.queryParam(fieldName, String.valueOf(fieldValue));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return spec;
    }

    public static MockMultipartHttpServletRequestBuilder uploadMockSupport(
        MockMultipartHttpServletRequestBuilder builder, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                Object fieldValue = Optional.ofNullable(field.get(obj)).orElseThrow();

                if (fieldValue instanceof MockMultipartFile) {
                    MockMultipartFile file = (MockMultipartFile) fieldValue;
                    builder = builder.file(fieldName, file.getBytes());
                } else if (fieldValue instanceof List) {
                    if (!((List<?>) fieldValue).isEmpty()) {
                        Object fieldObj = Optional.ofNullable(((List<?>) fieldValue).get(0))
                            .orElseThrow();
                        if (fieldObj instanceof MockMultipartFile) {
                            for (MockMultipartFile file : ((List<MockMultipartFile>) fieldValue)) {
                                builder = builder.file(fieldName, file.getBytes());
                            }
                        }
                    }
                } else {
                    builder = (MockMultipartHttpServletRequestBuilder) builder.param(fieldName,
                        String.valueOf(fieldValue));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder;
    }

    public static MockMultipartHttpServletRequestBuilder generateUploadMockPutBuilder(
        MockMultipartHttpServletRequestBuilder builder) {
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });
        return builder;
    }
}
