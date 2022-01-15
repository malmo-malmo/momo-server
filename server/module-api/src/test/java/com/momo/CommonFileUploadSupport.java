package com.momo;

import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

public class CommonFileUploadSupport {
    public static File uploadFile;

    static {
        try {
            uploadFile = new ClassPathResource("upload-test.png").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static RequestSpecification uploadAssuredSupport(RequestSpecification spec, Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                spec = spec.queryParam(field.getName(), String.valueOf(field.get(obj)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return spec;
    }
    public static MockMultipartHttpServletRequestBuilder uploadMockSupport(MockMultipartHttpServletRequestBuilder builder, Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                Object fieldValue = field.get(obj);

                if(fieldValue instanceof MockMultipartFile) {
                    builder = builder.file((MockMultipartFile) fieldValue);
                } else {
                    builder = (MockMultipartHttpServletRequestBuilder) builder.param(fieldName, String.valueOf(fieldValue));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return builder;
    }
}
