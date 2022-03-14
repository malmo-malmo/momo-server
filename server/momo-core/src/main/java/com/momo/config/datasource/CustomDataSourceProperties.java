package com.momo.config.datasource;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class CustomDataSourceProperties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private final Map<String, Slave> slave = new HashMap<>();

    @Getter @Setter
    public static class Slave {

        private String name;

        private String url;
    }
}