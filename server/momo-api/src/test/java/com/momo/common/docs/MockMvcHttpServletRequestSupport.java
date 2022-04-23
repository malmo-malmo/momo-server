package com.momo.common.docs;

import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class MockMvcHttpServletRequestSupport {

    public static MockMultipartHttpServletRequestBuilder mockMultipartPutBuilder(String urlTemplate) {
        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart(urlTemplate);
        builder.with(request -> {
            request.setMethod("PUT");
            return request;
        });
        return builder;
    }

    public static MockMultipartHttpServletRequestBuilder mockMultipartPatchBuilder(
        String urlTemplate,
        Object... uriVars
    ) {
        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart(urlTemplate, uriVars);
        builder.with(request -> {
            request.setMethod("PATCH");
            return request;
        });
        return builder;
    }
}
