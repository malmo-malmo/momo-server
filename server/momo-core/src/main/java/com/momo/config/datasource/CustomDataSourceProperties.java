package com.momo.config.datasource;

import static com.momo.Profile.PRODUCT;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@Profile(PRODUCT)
@ConfigurationProperties(prefix = "spring.datasource")
public class CustomDataSourceProperties {

    private Hikari hikari;
    private Master master;
    private final Map<String, Slave> slave = new HashMap<>();

    @Getter
    @Setter
    public static class Hikari {

        private int maximumPoolSize;
    }

    @Getter
    @Setter
    public static class Master {

        private String driverClassName;
        private String jdbcUrl;
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class Slave {

        private String name;
        private String jdbcUrl;
    }
}
